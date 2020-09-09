from django.db import models
from django.contrib.auth.models import AbstractUser,BaseUserManager,PermissionsMixin

class UserManager(BaseUserManager):
    def create_user(self, email, birth, password=None):
        if not email:
            raise ValueError('Users must have an email address')

        user = self.model(
            email=self.normalize_email(email),
            birth=birth,
        )

        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, email, birth, password):
        user = self.create_user(
            email,
            password=password,
            birth=birth,
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
    
    birth = models.DateField()
    gender = models.CharField(max_length=2)
    profile_img = models.TextField()

    msg = models.CharField(max_length=255, default="")
    subscribe = models.CharField(max_length=255, default="")

    objects = UserManager()

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = ['birth']

    def __str__(self):
        return self.email

    def has_perm(self, perm, obj=None):
        return True

    def has_module_perms(self, app_label):
        return True

    @property
    def is_staff(self):
        return self.is_admin