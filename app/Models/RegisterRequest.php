<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class RegisterRequest extends Model
{
    use HasFactory;
    protected $table = "rigisterrequest";      ### Rename table name if you want , it depends on your local DataBase ###
    protected $fillable=['contact_email','national_number','id_reference'];
    public $timestamps=false;
}
