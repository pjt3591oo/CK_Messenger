from django.db import models
from django.contrib.auth.models import AbstractUser,BaseUserManager,PermissionsMixin

class UserManager(BaseUserManager):
    def create_user(self, email, username, password=None):
        if not email:
            raise ValueError('Users must have an email address')

        user = self.model(
            email=self.normalize_email(email),
            username=username,
        )

        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, email, username, password):
        user = self.create_user(
            email=email,
            password=password,
            username=username,
        )
        user.is_admin = True
        user.save(using=self._db)
        return user

class User(AbstractUser):
    email = models.CharField(
        verbose_name='email',
        max_length=255,
        unique=True,
    )
    is_active = models.BooleanField(default=True)
    is_admin = models.BooleanField(default=False)
    
    username = models.CharField(
        max_length=255,
    )
    
    birth = models.DateField(null=True)
    gender = models.CharField(max_length=2, null=True)
    profile_img = models.TextField(null=True)

    msg = models.CharField(max_length=255, default="")
    subscribe = models.CharField(max_length=255, default="")

    objects = UserManager()

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = ['username']

    def __str__(self):
        return self.email

    def has_perm(self, perm, obj=None):
        return True

    def has_module_perms(self, app_label):
        return True

    @property
    def is_staff(self):
        return self.is_admin