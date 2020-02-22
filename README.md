# Java-Deserializer
This project is inteded to give the option to deserialize any java object to intermediate representation of the types, instances, and values.

Using Jar created by @unsynchronized to do all the heavy lifting.
https://github.com/unsynchronized/jdeserialize

Example Console Output
    
    read: User _h0x7e0002 = r_0x7e0000;  
    //// BEGIN stream content output
    User _h0x7e0002 = r_0x7e0000;  
    //// END stream content output
    
    //// BEGIN class declarations (excluding array classes)
    class User implements java.io.Serializable {
        java.lang.String ID;
        java.lang.String firstName;
        java.lang.String lastName;
        java.lang.String mobileNumber;
    }
    
    //// END class declarations
    
    //// BEGIN instance dump
    [instance 0x7e0002: 0x7e0000/User
      field data:
        0x7e0000/User:
            ID: r0x7e0003: [String 0x7e0003: "1234"]
            lastName: r0x7e0005: [String 0x7e0005: "Varlynx"]
            firstName: r0x7e0004: [String 0x7e0004: "Varlynx"]
            mobileNumber: r0x7e0006: [String 0x7e0006: "010"]
    ]
    //// END instance dump
    
    ///////////////////////////////////////////////////////
    read: SomeClass _h0x7e0003 = r_0x7e0000;  
    //// BEGIN stream content output
    SomeClass _h0x7e0003 = r_0x7e0000;  
    //// END stream content output
    
    //// BEGIN class declarations (excluding array classes)
    class java.lang.Double extends java.lang.Number implements java.io.Serializable {
        double value;
    }
    
    class java.lang.Number implements java.io.Serializable {
    }
    
    class SomeClass implements java.io.Serializable {
        int i;
        java.lang.Double d;
        java.lang.String s;
    }
    
    //// END class declarations
    
    //// BEGIN instance dump
    [instance 0x7e0006: 0x7e0004/java.lang.Double
      field data:
        0x7e0005/java.lang.Number:
        0x7e0004/java.lang.Double:
            value: -1.0
    ]
    [instance 0x7e0003: 0x7e0000/SomeClass
      field data:
        0x7e0000/SomeClass:
            i: 2147483647
            d: r0x7e0006: java.lang.Double _h0x7e0006 = r_0x7e0004;  
            s: r0x7e0007: [String 0x7e0007: "ABCDEFGHIJKLMNOP"]
    ]
    //// END instance dump
    
    
    Process finished with exit code 0
    
