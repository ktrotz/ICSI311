;KIMBERLEY TROTZ
;BTREE SCHEME - 04/05/20


;create tree
(define tree       '("R" 100 999(
                                
    ("R" 100 199 (
("L" 120 140 160 180)))       
                                ("R" 200 299 (
                            ("L" 220 240 260 280)))      
)))


;lamda functions
(define firstVal (lambda (tree)
 (car tree) )) ;set value to first element 

(define next (lambda (tree)
 (cdr tree))) ;set to 2nd element

(define minRoot (lambda (tree)
 (car(cdr tree)) )) ;set minimum of root nodes

(define maxRoot (lambda (tree)
 (car(cdr(cdr tree))) )) ;set maximum of root nodes

(define children (lambda (tree)
 (car(cdr(cdr(cdr tree)))) )) ;set variable 'children' to a list of the current root node's children


;search the tree
(define search (lambda (tree value)                  
 (cond
   ((null? tree) #f) ;check if tree is empty

   ;evaluate root node and find min and max values
   ((equal? (firstVal tree) "R") 
        (if (and (<= (minRoot tree) value) (>= (maxRoot tree) value)) 
            (search (children tree) value) )) ;recursively return list of children nodes
   
   ((equal? (firstVal tree) "L")
    (cond ((member value tree)
              (display "Value Found In Leaf Node: ") (display (member value tree)) )
           (else (display "Value Doesn't Exist In Tree")) )) ;evaluate if is leaf and determine if value exists in leaf node
   
 (else
   (cond ;if the search value is less than the maxRoot of the first tree node then search left  ; else search right
     ((<= value (maxRoot (firstVal tree))) (search (firstVal tree) value)) 
     (else (search (next tree) value)))
 )                    
))) 

(search tree 220)


