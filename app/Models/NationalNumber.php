<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Nationalnumber extends Model
{
    protected $table = "nationalnumbers";
    protected $fillable=['number'];
    public $timestamps=false;
}
