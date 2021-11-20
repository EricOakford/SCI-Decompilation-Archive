;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64920)
(include sci.sh)
(use Print)
(use System)


(class Array of Obj
	(properties
		scratch 0
		data 0
		type $ffff
	)
	
	(method (new param1 &tmp newSuper)
		(if (== self Array)
			(Prints {Can't create an array of base type Array!})
			(return 0)
		)
		(= newSuper (super new:))
		(newSuper
			data:
				(cond 
					(argc (self callKernel: 0 param1 type))
					(data (self callKernel: 8 data))
					(else (self callKernel: 0 10 type))
				)
		)
		(return newSuper)
	)
	
	(method (init param1)
		(self fill: 0 -1 (if argc param1 else 0))
	)
	
	(method (dispose)
		(self callKernel: 4 data)
		(super dispose:)
	)
	
	(method (with &tmp temp0)
		(= temp0 (self new: argc))
		(temp0 at: 0 &rest)
		(return temp0)
	)
	
	(method (newWith param1 &tmp temp0)
		(= temp0 (self new: (Max (- argc 1) param1)))
		(temp0 at: 0 &rest)
		(return temp0)
	)
	
	(method (at param1)
		(if (== argc 1)
			(return (self callKernel: 2 data param1))
		)
		(self callKernel: 3 data param1 &rest)
		(return self)
	)
	
	(method (copyToFrom param1 param2 param3 param4)
		(self
			callKernel: 6 data param1 (self callKernel: 9 param2) param3 param4
		)
		(return self)
	)
	
	(method (copy param1)
		(if (self isClass:)
			((self new:) copy: param1)
		else
			(self copyToFrom: 0 (self callKernel: 9 param1) 0 -1)
		)
	)
	
	(method (compToFrom param1 param2 param3 param4)
		(return
			(not
				(self
					callKernel: 7 data param1 (self callKernel: 9 param2) param3 param4
				)
			)
		)
	)
	
	(method (compare param1)
		(if
			(!=
				(self size:)
				(self callKernel: 1 (self callKernel: 9 param1))
			)
			0
		else
			(self compToFrom: 0 (self callKernel: 9 param1) 0 -1)
		)
	)
	
	(method (fill param1 param2 param3)
		(self
			callKernel: 5 data param1 param2 (if (== argc 3) param3 else 0)
		)
	)
	
	(method (move param1 param2 param3)
		(self copyToFrom: param1 self param2 param3)
	)
	
	(method (size)
		(self callKernel: 1 data)
	)
	
	(method (indexOf param1 param2 &tmp temp0 arraySize)
		(= arraySize (self size:))
		(if (and (> argc 1) param2)
			(= temp0 (-- arraySize))
			(while (>= temp0 0)
				(if (== (self at: temp0) param1) (return temp0))
				(-- temp0)
			)
		else
			(= temp0 0)
			(while (< temp0 arraySize)
				(if (== (self at: temp0) param1) (return temp0))
				(++ temp0)
			)
		)
		(return -1)
	)
	
	(method (callKernel)
		(Array &rest)
	)
)

(class IntArray of Array
	(properties
		scratch 0
		data 0
		type $0000
	)
)

(class IDArray of Array
	(properties
		scratch 0
		data 0
		type $0001
	)
)

(class ByteArray of Array
	(properties
		scratch 0
		data 0
		type $0002
	)
)
