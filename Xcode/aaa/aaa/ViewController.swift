//
//  ViewController.swift
//  aaa
//
//  Created by Yoshiki Sato on 5/23/29 H.
//  Copyright © 29 Heisei Yoshiki Sato. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

    /*Declaration*/
    @IBOutlet weak var fieldA: UITextField!
    @IBOutlet weak var fieldB: UITextField!
    @IBOutlet weak var result: UILabel!
    
    /*View load*/
    override func viewDidLoad() {
        super.viewDidLoad()
        fieldA.delegate = self
        fieldB.delegate = self
    }
        
    /*Get the is touching*/
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        if(fieldA.isFirstResponder){
            fieldA.resignFirstResponder()
        }else if(fieldB.isFirstResponder){
            fieldB.resignFirstResponder()
        }
        
    }
    
    /*Hide the keyboard touching outside*/
    func textFieldShouldClear(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    /*Hide the keyboard touching "Return"*/
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    /*About button*/
    @IBAction func calculate(_ sender: Any) {
        /*Get the int data converted from text data*/
        let resultA:Int? = Int(fieldA.text!)
        let resultB:Int? = Int(fieldB.text!)
        
        /*Hide the keyboard touching button*/
        if(fieldA.isFirstResponder){
            fieldA.resignFirstResponder()
        }else if(fieldB.isFirstResponder){
            fieldB.resignFirstResponder()
        }
        
        /*If textdata cannot convert,print error phrase*/
        if(resultA != nil && resultB != nil){
            result.text = "答え：" + String(resultA! * resultB!)
        }else{
            result.text = "入力値が不正です"
        }
    }

}

