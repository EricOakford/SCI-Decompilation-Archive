;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	ARRAY.SC
;;;;	(c) Sierra On-Line, Inc, 1989
;;;;
;;;;	Author: Jeff Stephenson
;;;;
;;;;	The Array classes.  These classes implement the concept of arrays
;;;;	of words (class IntArray), ids (class IDArray), and bytes (class
;;;;	ByteArray).  One difference between these classes and arrays as
;;;;	they are normally thought of is that these arrays grow when an attempt
;;;;	is made to write beyond the bounds of the array, rather than raising
;;;;	an "array bounds" violation.
;;;;
;;;;	Arrays cannot occupy more than 64K of memory (including headers, etc.
;;;;	used by the interpreter).  Since IntArray and IDArray have elements
;;;;	which occupy two bytes, they are limited to several less than 32K
;;;;	elements.  ByteArray (and its sub-class String) have one byte elements
;;;;	and thus may have almost 64K elements.
;;;;
;;;;	Classes:
;;;;		Array
;;;;		IntArray
;;;;		IDArray
;;;;		ByteArray


(script#	ARRAY)
(include game.sh)
(use Print)
(use System)


(class Array of Object
;;; The Array class is an abstract class which implements the array methods
;;; used by its sub-classes, but which is never instantiated as an object.

	(properties
		data 	0			;The ID of the kernel array block
		type	-1			;The type of array
	)

;;;	(methods
;;;		with				;create an instance of an array and initialize it
;;;		newWith			;create an instance of a given size and initialize it
;;;		at					;return or set the value of the nth element
;;;		copyToFrom		;copy a block from one array to another
;;;		copy				;copy an entire array
;;;		compToFrom		;compare a block from one array with another
;;;		compare			;compare two arrays
;;;		fill				;fill a block in an array with a given value
;;;		move				;move a block within an array
;;;		size				;return the size of an array
;;;		indexOf			;return the index of a character in a string
;;;		callKernel		;make the call to the appropriate kernel function
;;;							; (overridden by subclasses)
;;;	)


	(method (new n &tmp array)
	;; Return a new array capable of holding n elements.  The array is
	;; initialized to all 0's
	;;
	;; Example:
	;;	(IDArray new: 10)
	;; returns the ID of an array of IDs [0 0 0 0 0 0 0 0 0 0]

		(if (== self Array)
			(Prints {Can't create an array of base type Array!})
			(return NULL)
		)

		(= array (super new:))
		(array data:
			(cond
				(argc
					(self callKernel: ArrayNew n type)
				)
				(data
					(self callKernel: ArrayDup data)
				)
				(else
					(self callKernel: ArrayNew 10 type)
				)
			)
		)
		(return array)
	)


	(method (with &tmp array)
	;; Return a new array containing the arguments as elements.  The array
	;; will hold as many elements as there are arguments.
	;;
	;; Example:
	;;	(IntArray with: 1 2 3 4 5)
	;; returns the ID of an array of integers [1 2 3 4 5].

		(= array (self new: argc))
		(array at: 0 &rest)
		(return array)
	)


	(method (newWith n &tmp array)
	;; Return a new array capable of holding n elements, with the first
	;; elements containing the rest of the arguments.
	;;
	;; Example:
	;;	(IntArray newWith: 10 1 2 3 4 5)
	;; returns the ID of an array of integers [1 2 3 4 5 0 0 0 0 0]

		(= array (self new: (Max (- argc 1) n)))
		(array at: 0 &rest)
		(return array)
	)


	(method (at n)
	;; If only 'n' is passed, return the value of the nth element of
	;; the array.  If more arguments are passed, place them in successive
	;; elements of the array starting with the nth and returns the ID
	;; of the array.
	;;
	;; Example:
	;;	(= array (ByteArray with: 1 2 3 4 5))
	;;	(array at: 3)
	;; returns 4 and
	;;	(array at: 3 6 6 6 6)
	;; returns the ID of array, which is now [1 2 3 6 6 6 6].  Note that
	;; the array has grown, since we wrote past its end.

		(if (== argc 1)
			(return (self callKernel: ArrayAt data n))
		)
		(self callKernel: ArrayAtPut data n &rest)
		(return self)
	)


	(method (copyToFrom to source from length)
	;; Copy 'length' elements from the array 'source' to the receiver.
	;; Start copying from element 'from' in the source to element 'to'
	;; in the receiver.  Return the receiver.
	;;
	;; Example:
	;;		(= array1 (IntArray with: 1 2 3 4 5))
	;;		(= array2 (IntArray with: 5 4 3 2 1))
	;;		(array1 copyToFrom: 3 array2 3 2)
	;; returns array1, which is now [1 2 3 2 1]

		(self callKernel: ArrayCpy data to (self callKernel: ArrayGetData source) from length)
		(return self)
	)


	(method (copy source)
	;; Copy the entire array 'source' to the reciever.  Return the receiver.
	;;
	;; Example:
	;; With array1 & array2 as in copyToFrom:,
	;;		(array1 copy: array2)
	;; returns array1, which is now [5 4 3 2 1]

		(return
			(if (self isClass:)
				((self new:) copy: source)
			else
				(self copyToFrom: 0 (self callKernel: ArrayGetData source) 0 -1)
			)
		)
	)


	(method (move to from length)
	;; Copy within the receiver.  Copy 'length' elements starting at
	;; 'from' to the elements starting at 'to'.  [Overlapping copies are
	;; handled correctly -- the source will not be trashed by the destination.]
	;;
	;; Example:
	;; With array1 as in copyToFrom:,
	;;		(array1 move: 2 1 3)
	;; returns array1, which is now [1 2 2 3 4]

		(return (self copyToFrom: to self from length))
	)


	(method (compToFrom to source from length)
	;; Compare blocks in two arrays.  Return TRUE if the 'length' elements
	;; of 'source' starting at 'from' are the same as the elements in the
	;; receiver starting at 'to'.  Return FALSE otherwise.
	;;
	;; Example:
	;;		(= array1 (ByteArray with: 1 2 3 4 5))
	;;		(= array2 (ByteArray with: 3 2 1 3 4))
	;;		(array1 compToFrom: 2 array2 3 2)
	;; returns TRUE;

		(return
			(not		; <- this because our StrCmp returns TRUE if different
				(self callKernel: ArrayCmp data to (self callKernel: ArrayGetData source) from length)
			)
		)
	)


	(method (compare source)
	;; Compare two arrays.  Return TRUE if the arrays are the same size
	;; and the values of the elements match.
	;;
	;; With array1 & array2 as in compToFrom:,
	;;		(array1 compare: array2)
	;; returns FALSE.

		(return
			(if (!= (self size:) (self callKernel: ArraySize (self callKernel: ArrayGetData source)))
				FALSE
			else
				(self compToFrom: 0 (self callKernel: ArrayGetData source) 0 -1)
			)
		)
	)


	(method (fill from length val)
	;; Set 'length' elements of the receiver starting at 'from' to the
	;; value 'val'.
	;;
	;; Example:
	;;		(= array (IntArray with: 1 2 3 4 5))
	;;		(array fill: 2 2 6)
	;; returns array, which is now [1 2 6 6 5]

		(return (self callKernel: ArrayFill data from length (if (== argc 3) val else 0)))
	)


	(method (init val)
	;; Set all the elements of the receiver to 'val' if an argument is
	;; passed, otherwise set them all to 0.
	;;
	;; Example:
	;; With array as in fill:,
	;;		(array init:)
	;; returns array, which is now [0 0 0 0 0]

		(return (self fill: 0 -1 (if argc val else 0)))
	)


	(method (size)
	;; Return the number of elements which the array can hold without
	;; expansion.
	;;
	;; Example:
	;; 	((ByteArray with: 1 2 3 4 5) size:)
	;; returns 5.

		(return (self callKernel: ArraySize data))
	)


	(method (dispose)
		(self callKernel: ArrayFree data)
		(super dispose:)
	)


	(method (indexOf val fromRight &tmp i len)
	;; Returns the index of 'val' in the receiver, or -1 if it is not present.
	;; If 'fromRight' is present and TRUE, returns the index of the first 
	;; occurance from the right, otherwise returns the index of the first 
	;; occurance from the left.
	;;
	;; Example:
	;;	(= positions (XXXArray with: 0 5 2 6))
	;;	(positions indexOf: 2)
	;;		returns 2

		(= len (self size:))

		(if (and (> argc 1) fromRight)
			(for	((= i (-- len))) (>= i 0) ((-- i))
				(if (== (self at: i) val) 
					(return i)
				)
			)
		else
			(for	((= i 0)) (< i len) ((++ i))
				(if (== (self at: i) val) 
					(return i)
				)
			)
		)

		(return -1)
	)

	(method (callKernel)
	;; Simply calls the KArray kernel call with the rest of the arguments.
	;; This method is overridden in subclasses that require calling another
	;;	kernel entry besides KArray.

		(Array &rest)
	)
)



(class IntArray of Array
;;; An array of integers.

	(properties
		type	WORDARRAY
	)
)



(class IDArray of Array
;;; An array of IDs.

	(properties
		type	IDARRAY
	)
)



(class ByteArray of Array
;;; An array of bytes.

	(properties
		type	BYTEARRAY
	)
)