<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class RegisterRequest extends Model
{
    protected $table = "rigisterrequest";
    protected $fillable=['contact_email','national_number','id_reference'];
    public $timestamps=false;
      
}
