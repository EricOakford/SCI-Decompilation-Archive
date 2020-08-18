;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	STRING.SC
;;;;	(c) Sierra On-Line, Inc, 1989
;;;;
;;;;	Author: Jeff Stephenson
;;;;
;;;;	The String class.
;;;;
;;;;	Classes:
;;;;		String

;EO: All instances of KString have been replaced with "String" to allow the script to compile.
;Restore the original name when SCICompanion recognizes SCI32 kernel calls.

(script#	STRING)
(include game.sh)
(use Array)

;;;(procedure
;;;	SizeOfStr
;;;)


(public
	SizeOfStr	0
)



(class String kindof ByteArray
;;; Unless you're writing a word processor in Script and need all the
;;; performance you can get, the String class, rather than the KString
;;; kernel call, is the preferred way to manipulate strings.
;;;
;;; One of the strong points of Strings is that they grow automatically
;;; to accomodate longer strings -- you needn't worry about overflowing
;;; the storage allocated to them.

	(properties
		name	"Str"
		type	STRARRAY
	)

;;;	(methods
;;;		format		;	Returns new string formatted like Printf stmt.
;;;		newCopies	;	New string of multiple copies of a char or subStr
;;;		cat			;	Concatenate another string to myself
;;;		translate	;	Translate all substrings within self to new values
;;;		all			;	Like translate, but drops out parts that don't match
;;;		upper			;	Replace all lowercase letters with uppercase
;;;		lower			;	Replace all uppercase letters with lowerase
;;;		subStr		;	Return a substring of a string
;;;		left			;	Obtain new string of the nth leftmost digits
;;;		right			;	Obtain new string of the nth rightmost digits
;;;		getToken		;	Get a token within self, (modifies self)
;;;		strip			;	Strip white space from self (similar to REXX strip)
;;;		strIndex		;	Return the index of a substring in a string
;;;		asInteger	;	Return the numeric value represented by a string
;;;		strMax		;	Return the num of chars I can hold without expansion
;;;		weigh			;	Alphabetic comparison of self to another string
;;;	)

	(method (format &tmp str)

;; 'printf' into a string.  If this is sent to the String class, it
;; returns a new string object.  Otherwise it replaces the string in
;; the receiver, returning the receiver.

		(if (& -info- CLASS)
			(= str (Clone self))
			(str data: (self callKernel: StrFormat &rest))
			(return str)
		else
			(self callKernel: StrFormatAt data &rest)
			(return self)
		)
	)


	(method (with aStr &tmp str)

;; Return a String object with 'aStr' as its string.
;;
;; Example:
;;	(String with: "foobar")
;; returns a string with value "foobar".

		(= str (Clone self))
		(str data: (self callKernel: StrDup (self callKernel: StrGetData aStr)))
		(return str)
	)


	(method (newWith n aStr &tmp str)

;; Returns a String object capable of holding at least 'n' characters
;; which initially contains the string 'aStr'.
;;
;; Example:
;;	(String newWith: 10 "test")
;; returns a 10 character string containing "test".

		(= str (String new: n))
		(if aStr
			(str copy: aStr)
		)
		(return str)
	)


	(method (newCopies whatToCopy 	;str, datablock, or char to make copies of
							 howMany 		;number copies to make, (default is 1)
							 isChar		 	;optional, T/F declare 1st parm a datablock
							 
							 &tmp 
							 str 				;temp str to return as the created string
							 numCopies		;contains what howMany does, when given
							 strToCopy		;
							 
			  )
;;
;;	Returns a new string of 'howMany' copies of the passed 'whatToCopy'. 
;; You may use either strings, characters or data blocks for copying.
;;
;; Examples:
;;	(= a (String with: "abc"))					; "abc"
;;	(= b (String newCopies: a 2))				; "abcabc"
;;	(= c (String newCopies: "xy" 4))			; "xyxyxyxy"
;;	(= d (String newCopies: `+ 5))			; "+++++"
;;
;;	Datablock strings for copying, like example (c) above, will work.
;;	However, if this method gets a block address of less than 256 (which 
;;	is extremely unlikely), the code has no choice but to think it's a 
;;	character. If this possibilty worries you, pass a TRUE as the third
;;	parameter when you use a data block instead of a true string object.

		(if (< argc 1)
			(return (String new:))	;default to clean string on no parms
		)

		(if (< argc 2)
			(= numCopies 1)			;omitted howMany? Default to 1.
		 else
		 	(= numCopies howMany)
		)

		(if (and (> argc 2) isChar)
			; They passed us a character (e.g. `+)
			(= str (String new: numCopies))
			(str fill: 0 numCopies whatToCopy)
		else
			; They passed us a string object or string literal
			(= str (String newWith: (* numCopies (SizeOfStr whatToCopy))))
			(= strToCopy (String StrGetData whatToCopy))	;EO: Was "KString"
			(while (>= (-- numCopies) 0)
				(str cat: strToCopy)
			)
		)
		(return str)
	)


	(method (at n &tmp c)

;; If only 'n' is passed, return the character at position 'n' (0 based)
;; of the string.
;; If more arguments are passed, place them in the string starting at
;; position 'n' and return the receiver.
;;
;; Example:
;;	(= str (String with: "A test."))
;;	(str at: 2)
;;		returns 't'
;;	(str at: 2 `T)
;;		returns 'str', whose string is now "A Test."

		(= c (super at: n &rest))
		(if (> argc 1)
			(return self)
		)
		(return c)
	)


	(method (cat aStr)

;; Concatenate the string 'aStr' onto the end of the string of the
;; receiver.
;;
;; Example:
;;	(= str1 (String with: "foo"))
;;	(= str2 (String with: "bar"))
;;	(str1 cat: str2)
;;		leaves str1 with the string "foobar"

		(return
			(self copyToFrom: (self size:) aStr 0 (+ (SizeOfStr aStr) 1))
		)
	)

	(method (translate 			
						oldSub		; old string to substitute for
						newSub 		; new substitution string to replace old one

						&tmp
						i		 		; loop index
						subCount 	; count of substitutions made
						selfSize		; length in usable chars of myself
						subSizeOld	; length of the old substitution string
						subSizeNew	; length of the new substitution string
						rightStr 	; what is on the right of the oldSub
			  )
;;
;; Modifies self by replacing all occurrances of 'oldSub' substitution
;;	string with the 'newSub' substitution string, and returns the number
;; of times a substitution took place.
;;
;; Examples:
;;	(= str (String with: "///+++:::///+++")) 	;"///+++:::///+++"
;;	(str translate: "///" "#")			 			;"#+++:::#+++", 	(ret 2)
;;	(str translate: "+++" "&&&&")			 		;"#&&&&:::#&&&&", (ret 2)
;;	(str translate: "[[[" "///")			 		;"#&&&&:::#&&&&", (ret 0)

		(if (< argc 2)
			(return 0)
		)

		(self callKernel: StrTrn data oldSub newSub data)

		(return subCount)
	)


	(method (all 			
						oldSub		; old string to substitute for
						newSub 		; new substitution string to replace old one

						&tmp
						i		 		; loop index
						lag_i			; lagging index within myself to next char
						subCount 	; count of substitutions made
						selfSize		; length in usable chars of myself
						subSizeOld	; length of the old substitution string
						subSizeNew	; length of the new substitution string
						makeStr		; string to make which replaces self
			  )
;;
;; Modifies self by replacing all occurrances of 'oldSub' substitution
;;	string with the 'newSub' substitution string, and returns the number
;; of times a substitution took place. Patters "in between" that did
;;	not match are removed from self.
;;
;;	If "newSub" is omitted, this method simply retuns the number of matches
;;	and removes anything is self that is not an "oldSub" subString.
;;
;; Examples:
;;	(= str (String with: "///+++:::///+++")) 	;"///+++:::///+++"
;;	(str all: "///" "#")			 					;"##"					(ret 2)
;;	(= str (String with: "///+++:::///+++")) 	;"///+++:::///+++"
;;	(str all: "///+++")			 					;"///+++///+++", 	(ret 2)

		(if (< argc 1)
			(return 0)
		)

		(= subCount 0)
		(= lag_i 0)
		(= selfSize (self size:))
		(= subSizeOld (SizeOfStr oldSub))
		(= subSizeNew (SizeOfStr newSub))
		(= makeStr (String new: (+ (/ selfSize 2) 1)))	;just a guess at the size...

		(for	((= i 0))
				(<= i (- selfSize subSizeOld))
				((++ i))

			(if (> (self compToFrom: i oldSub 0 subSizeOld) 0)
				(if (== argc 2)
					(if subSizeNew
						(makeStr	copyToFrom: lag_i newSub 0 subSizeNew)
						(+= i (- subSizeNew 1))
						(+= lag_i subSizeNew)
					)
				 else
				 	(if subSizeOld
						(makeStr	copyToFrom: lag_i oldSub 0 subSizeOld)
						(+= i (- subSizeOld 1))
						(+= lag_i subSizeOld)
					)
				)
				(++ subCount)
			)
		)
		(self copyToFrom: 0 makeStr 0 lag_i)
		(self at: lag_i 0)
		(makeStr dispose:)
		(return subCount)
	)


	(method (upper &tmp i mySize cur)
;; 
;;	Upcase all letters in self.
;;
;; Example:
;;	(= a (String with: " 1 Test"))			;" 1 Test"
;;	(a upper:)										;" 1 TEST"

		(= mySize (self size:))
		(for ((= i 0)) (< i mySize) ((++ i))
			(= cur (self at: i))			
			(if (and (>= cur `a)
						(<= cur `z)
				 )
				(self at: i (- cur 32))
			)
		)
	)


	(method (lower &tmp i mySize cur)
;; 
;;	Lowercase all letters in self.
;;
;; Example:
;;	(= a (String with: " 1 Test"))			;" 1 Test"
;;	(a lower:)										;" 1 test"

		(= mySize (self size:))
		(for ((= i 0)) (< i mySize) ((++ i))
			(= cur (self at: i))			
			(if (and (>= cur `A)
						(<= cur `Z)
				 )
				(self at: i (+ cur 32))
			)
		)
	)


	(method (subStr n len &tmp l)

;; Returns a new String object whose string is the substring of length
;; 'len' of the receiver starting at position 'n'.
;;
;; Example:
;;	((String with: "A test.") subStr: 2 4)
;;		returns a String object with string "test"

		(= l
			(if (== len -1)
				(- (self size:) n)
			else
				len
			)
		)
		(return
			((String new: (+ l 1))
				copyToFrom: 0 self n l,
				at: l 0,
				yourself:
			)
		)
	)

	(method (left charCount 	;how many chars to expect on the left
					  padAsked 		;pad char, if omitted default to space
					  
					  &tmp 
					  padChar		;pad char copied from padAsked
					  str				;string to return to user
					  mySize			;size of self in chars
					  padStr			;padding string used if necessary
			  )

;; Returns a new string of the leftmost chars of self. If the string
;; returned would not be as long as 'charCount', pad with the padChar
;; to the needed length. (This lets you use the 'left' method to left
;; justify text within a new string of 'charCount' length).
;;
;;	Space is the default pad. You can turn padding off completely by
;;	using null for the pad char.
;;
;; Examples:
;;	(= str (String with: "123"))			 ;	"123"
;;	(= leftStr (str left: 2))				 ; "12"
;; (= leftStr (str left: 5 `*))			 ; "123**"
;; (= leftStr (str left: 5 0))			 ; "123"

		(if (< argc 1)
			(return (String new:))	; no args? return a null string
		)

		(if (< argc 2)
			(= padChar 32)				; default pad is space
		 else
			(= padChar padAsked)
		)

		(if (< charCount 1)
			(return (String new:))	; less than 1 leftmost char? Give this
		)									;lamebrain programmer a null string.

		(= mySize (self size:))

		(= str (self subStr: 		;get those leftmost chars in str
							0 
							(if (> charCount mySize) mySize else charCount)
				 )
		)

		(if (and (> charCount mySize) ;do padding if padChar is not a null
					padChar
			 )
			(= padStr (String newCopies: padChar (- charCount mySize)
						 )
			)
			(str cat: padStr)
			(padStr dispose:)
		)

		(return str)
	)

	(method (right charCount		;how many chars to expect on the right
					  	padAsked 		;pad char, if omitted default to space
					  
					  	&tmp 
					  	padChar			;pad char copied from padAsked
					  	str				;string to return to user
					  	mySize			;size of self in chars
					  	padStr			;padding string used if necessary
			  )

;; Returns a new string of the rightmost chars of self. If the string
;; returned would not be as long as 'charCount', pad with the padChar
;; to the needed length. (This lets you use the 'right' method to right
;; justify text within a new string of 'charCount' length).
;;
;;	Space is the default pad. You can turn padding off completely by
;;	using a null for the pad char. (Note how using a character 0 is 
;;	helpful to right justify integers.)
;;
;; Examples:
;;	(= str (String with: "123"))				 ;	"123"
;;	(= rightStr (str right: 2))				 ; "23"
;; (= rightStr (str right: 5 `0))			 ; "00123"
;; (= rightStr (str right: 5 0))			 	 ; "123"

		(if (< argc 1)
			(return (String new:))		; no args? return a null string
		)

		(if (< argc 2)
			(= padChar 32)				  	; default pad is space
		 else
			(= padChar padAsked)
		)

		(if (< charCount 1)
			(return (String new:))
		)

		(= mySize (self size:))

		(= str (self subStr: 
							(if (> charCount mySize) 0 	  else (- mySize charCount))
							(if (> charCount mySize) mySize else charCount)
				 )
		)

		(if (and (> charCount mySize) ;do padding if padChar is not a null
					padChar
			 )
			
			(= padStr (String newCopies: padChar (- charCount mySize)
						 )
			)
			(padStr cat: str)
			(str dispose:)
			(= str padStr)
		)

		(return str)
	)


	(method (indexOf c fromRight &tmp i len)

;; Returns the index of character 'c' in the receiver, or -1 if it is
;; not present.  If 'fromRight' is present and TRUE, returns the index
;; of the first occurance from the right, otherwise returns the index of
;; the first occurance from the left.
;;
;; Example:
;;	(= str (String with: "A test."))
;;	(str indexOf: `t)
;;		returns 2
;;	(str indexOf: `t TRUE)
;;		returns 5

		(= len (self size:))

		(if (and (> argc 1) fromRight)
			(for	((= i (-- len)))
					(>= i 0)
					((-- i))

				(if (== (self at: i) c) (return i))
			)
		else
			(for	((= i 0))
					(< i len)
					((++ i))

				(if (== (self at: i) c) (return i))
			)
		)

		(return -1)
	)


	(method (strIndex str fromRight &tmp selfLen strLen i)

;; As in 'indexOf' above, but searches for a string rather than a
;; character.
;;
;; Example:
;;	(= str (String with: "the test of the pudding"))
;;	(str strIndex: "the")
;;		returns 0
;;	(str strIndex: "the" TRUE)
;;		returns 12


		(= selfLen (self size:))
		(= strLen (SizeOfStr str))
		(if (and (> argc 1) fromRight)
			(for	((= i (- (- selfLen strLen) 1)))
					(>= i 0)
					((-- i))

				(if (self compToFrom: i str 0 strLen)
					(return i)
				)
			)
		else
			(for	((= i 0))
					(<= i (- selfLen strLen))
					((++ i))

				(if (self compToFrom: i str 0 strLen)
					(return i)
				)
			)
		)
		(return -1)
	)



	(method (asInteger)

;; Returns the integral value represented by the receiver's string.
;; If the string is not a number, returns 0.
;;
;; Example:
;;	((String with: "123") asInteger:)
;;		returns 123
;;	((String with: "foo") asInteger:)
;;		returns 0

		(return (self callKernel: StrToInt data))
	)

	(method (size)

;; Returns the number of characters in the string, similar to C's strlen().
;;
;; Example:
;;	((String with: "1234") length:)
;;		returns 4

		(return (self callKernel: StrLen data))
	)


	(method (init)
;; Set the current string to a null string.

		(self at: 0 0)
	)


	(method (strMax)
	;; Return the maximum length of the string without resizing.

		(return (- (super size:) 1))
	)


	(method (compare source)

;; Compare two arrays.  Return TRUE if the arrays are the same size
;; and the values of the elements match.
;;
;; With array1 & array2 as in compToFrom:,
;;		(array1 compare: array2)
;; returns FALSE.

		(return
			(if (!= (self size:) (SizeOfStr source))
				FALSE
			else
				(self compToFrom: 0 (self callKernel: StrGetData source) 0 (self size:))
			)
		)
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
			(not		; <- this because strcmp returns TRUE if different
				(self callKernel: StrCmp data (self callKernel: StrGetData source) length)
			)
		)
	)


	(method (getToken seperators token &tmp i c tok)

;;
;; 'seperators' is a string containing the characters which are token seperators.
;; This method returns the first token (characters not in seperators which are
;; bounded by seperator or NULL characters) in the string.  If 'token' is present,
;; the token is placed there.  If not, it is created as a new string.
;;
;;	NOTE: The String being tokenized is modified -- the token is removed.  Use a
;; copy if you really want to keep the String.

		;; Scan off seperators
		(while (!= (seperators indexOf: (self at: 0)) -1)
			(self move: 0 1 -1)
		)
		(if (== (self at: 0) 0)
			(return NULL)
		)

		(= tok (if (== argc 2) token else (String new:)))

		;; Build token until a seperator is encountered.
		(for	((= i 0) (= c (self at: 0)))
				(and c (== (seperators indexOf: c) -1))
				((++ i) (= c (self at: 0)))

			(tok at: i c)
			(self move: 0 1 -1)
		)
		(tok at: i 0)

		(return tok)
	)

	(method (strip actions 		;args: #left #right #center #all #show
						&tmp 
						stripLeft 	;boolean flag
						stripRight 	;boolean flag
						stripCenter ;boolean flag
						stripAll		;boolean flag
						rightMost	;index in myself of rightMost nonWhite character
						hitAlpha		;boolean true I've hit an alpha from the left
						showChar		;one white char user says NOT to strip away
						cur			;current working character while in loops
						mySize		;size of myself in chars
						i				;temp index
						lag_i			;lagging index because of space compression
						stripWord	;bit-packed word
			  )
;;
;; Strip white space	from the left, right, center, all, or some combination 
;; thereof. This method will modify self, so make a copy if you don't
;; want to lose the original.
;;
;;	If no arguements were given, default to stripping left and right.
;;
;;	If you specify a character with #show, that character, (even if it
;;	is white), will NOT be stripped.
;;
;;	Example #1:
;;	(= a (String with: " 1  2   3    "))		;	" 1  2   3    "
;;	(a strip:)											;	"1  2   3"
;;
;;	Example #2:
;;	(= a (String with: " 1  2   3    "))		;	" 1  2   3    "
;;	(a strip: #left #center)						;	"123    "
;;
;;	Example #3:
;;	(= a (String with: " 1  2   3    "))		;	" 1  2   3    "
;;	(a strip: #all)									;	"123"
;;
;;	Example #4:
;;	(= a (String with: "<tab><space>1<tab>"))	;	"<tab><space>1<tab>"
;;	(a strip: #all #show 32)						;	" 1"

		(= actions actions)	;turn off compiler warning

		(= stripLeft 	FALSE)
		(= stripRight 	FALSE)
		(= stripCenter FALSE)
		(= stripAll 	FALSE)
		(= showChar		0)
		(= stripWord	0)
		
		(if (< argc 1)
			(= stripLeft 	TRUE)		;no args? Default to strip left and right
			(= stripRight 	TRUE)
		 else
		 	(= i 0)						;parse up args to decide how to strip self
			(while (< i argc)
				(switch [actions i]
					(#left
						(= stripLeft 	TRUE)
						(|= stripWord $0004)
					)
					(#right
						(= stripRight 	TRUE)
						(|= stripWord $0001)
					)
					(#center
						(= stripCenter TRUE)
						(|= stripWord $0002)
					)
					(#all
						(= stripAll		TRUE)
						(= stripWord $0007)
					)
					(#show
						(= showChar [actions (+ i 1)])
						(++ i)
					)
				)
				(++ i)
			)
		)

		(if showChar
			(self callKernel: StrTrim data stripWord showChar)
		else
			(self callKernel: StrTrim data stripWord)
		)

	)


	(method (weigh strB &tmp strA loopInt curCharA curCharB aSize bSize)
;
;	Weight of self against another string (case sensitive alphabetic compare.)
;
;	Ex: (= str1 (String with: "abc"))
;		 (= str2 (String with: "abcx"))
;		 (= wt (str1: weigh str2))			returns -1 to the variable 'wt'
;
;	Returns: -1 when strA < strB, 0 when strA = strB, 1 when strA > strB
;
;
		(= strA self)
	
		(= aSize (self size:))
		(= bSize (strB size:))

		(for ((= loopInt 0)) 
		  	(and (< loopInt aSize) (< loopInt bSize))
		  	((++ loopInt))

			(= curCharA (strA at: loopInt))
			(= curCharB (strB at: loopInt))

			(if (!= curCharA curCharB)
				(if (< curCharA curCharB)
					(return -1)
			 	else
			 		(return 1)
				)
			)
		)

		(cond
			((== aSize bSize)
				(return 0)
			)
			((< aSize bSize)
				(return -1)
			)
			(else
				(return 1)
			)
		)
	)

	(method (callKernel)
		(String &rest)	;EO: Was "KString"
	)
)


(procedure (SizeOfStr str)
;; Return the size of a the kernel string block of 'str'.  This works
;; regardless of whether 'str' is a String object or a kernel string block.

	(return
		(String StrLen (String StrGetData str))	;EO: Was "KString"
	)
)
