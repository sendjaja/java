/*
import java.lang.Runnable;

class Racer implements Runnable {
    private String name;

    public Racer(String name) {
        this name = name;

    } 

    public void run() {
        try {
            Thread.sleep(100);
            System.out.print(name);
        } catch (InterruptedException e) {}
    }

    public static void main(String[] args) {
        
    }
}
*/

/*
public final class Utilities {
    private Utilities() { throw new IllegalStateException("Cannot be instantiated"); }
    public static void main(String args[]) {
    }
}
*/

/*
public class Hello {

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
*/

/* 
Firedragon implements Reptile interface
When a reptileEgg hatches, a new reptile will be created of the same species that laid the egg
An IllegalStateException is thrown if a ReptileEgg tries to hatch more than once


import java.util.concurrent.Callable;

interface Reptile {
    ReptileEgg lay();
}

class FireDragon {
    public FireDragon() {
    }

    public static void main(String[] args) throws Exception {
        FireDragon fireDragon = new FireDragon();
        System.out.println(fireDragon instanceof Reptile);
    }
}

class ReptileEgg {
    public ReptileEgg(Callable<Reptile> createReptile) {
        throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public Reptile hatch() throws Exception {
        throw new UnsupportedOperationException("Waiting to be implemented.");
    }
}

*/


// import java.util.concurrent.Callable;

// interface Reptile {
//     ReptileEgg lay();
// }

// class FireDragon implements Reptile {
//     public FireDragon() {
//         super();
//         System.out.println("FireDragon Super type is " + super.toString());
//     }

//     public ReptileEgg lay() {
//         return new ReptileEgg(new Callable<Reptile>() {
//             @Override
//             public Reptile call() {
//                 return new FireDragon();
//                 // return this.super();
//             }
//         });
//     }

//     public static void main(String[] args) throws Exception {
//         FireDragon fireDragon = new FireDragon();
//         System.out.println(fireDragon instanceof Reptile);

//         ReptileEgg newEgg = fireDragon.lay();
//         System.out.println(newEgg instanceof Reptile);
//         // System.out.println(newEgg instanceof FireDragon); //Incompatible conditional operand types ReptileEgg and FireDragon

//         Reptile newCreature = newEgg.hatch();
//         System.out.println(newCreature instanceof Reptile);
//         System.out.println(newCreature instanceof FireDragon);

//         // Reptile newCreature1 = newEgg.hatch(); //throw new IllegalStateException();
//     }
// }

// class ReptileEgg {
//     private static boolean hatched = false;
//     private Object type;

//     public ReptileEgg(Callable<Reptile> createReptile) {
//         // throw new UnsupportedOperationException("Waiting to be implemented.");
//         System.out.println("ReptileEgg Super type is " + super.getClass());
//         this.type = createReptile.getClass();
//         System.out.println("new Reptile Egg " + this.type.toString());
//     }

//     public Reptile hatch() throws Exception {
//         // throw new UnsupportedOperationException("Waiting to be implemented.");
//         if(!hatched) {
//             hatched = true;
//             // FireDragon a = new FireDragon();
//             // return (Reptile)a;
//             //
//             /*
//             System.out.println("type is : " + this.type.toString());
//             Reptile a = new FireDragon();
//             return a;
//             */
//             //
//             Class<?> test = Class.forName(this.type.toString());
//             Reptile a = (Reptile)test.cast(type);
//             return a;
//             //
//             /*
//             return new Reptile() {
//                 // @Override
//                 public ReptileEgg lay() {
//                     Callable<Reptile> newCreature = new Callable<Reptile>(){
//                         @Override
//                         public Reptile call() throws Exception {
//                             throw new IllegalStateException();
//                         };
//                     };
//                     return new ReptileEgg(newCreature);
//                 }
//             };
//             */
//         }
//         else
//             throw new IllegalStateException();  
//     }
// }
//
//
/*
package creator;

import chicken.Chicken;
import chicken.Egg;
*/
interface Bird {
    public Egg lay();
};

class Egg {
    final Object first;
     
    public Egg(Chicken mom) {
        this(mom.first);
    }
     
    private Egg(Object first) {
        this.first = first;
    }

    public Chicken hatch(Egg current) {
        return new Chicken(current);
    }
}

class Chicken implements Bird {
    final Object first;
     
    public Chicken(Egg egg) {
        first = egg.first;
    }

    public Egg lay() {
        return new Egg(this);
    }
     
    public void ask() {
        // The goal is to reach this line
        System.out.println("First there was the " + first + this);
    }
}

class Creator {
    static class FirstEgg extends Egg {
        FirstEgg() {
            super(null);
        }
 
        @Override
        protected void finalize() {
            new Chicken(this).ask();
        }
    }
 
    private static Chicken reference;

    public static void main(String[] args) throws Exception {
        try {
            new Chicken(null) {
                @Override
                protected void finalize() throws Throwable {
                    reference = this;
                }
            };
        } 
        catch (NullPointerException ignore) {            
        }      
        // } catch (NullPointerException e) {
        //}
         
        // there are ways to force garbage collection harder,
        // but this works good enough for me 
        System.gc();
        System.runFinalization();
        Thread.sleep(1000);

        if(reference != null) {
            reference.ask();
        }

        Egg newegg = reference.lay();
        Chicken first_chick = newegg.hatch(newegg);
        first_chick.ask();

        Egg secondegg = first_chick.lay();
        Chicken second_chick = secondegg.hatch(secondegg);
        second_chick.ask();
    }
}