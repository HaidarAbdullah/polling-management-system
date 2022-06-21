<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class NationalNumber extends Model
{
    use HasFactory;
    protected $table = "nationalnumbers";    // Rename table name if you want , it depends on your local DataBase
    protected $fillable=['number'];
    public $timestamps=false;
}
