;;; Sierra Script 1.0 - (do not remove this comment)
(script# 962)
(include sci.sh)
(use Procs)
(use String)
(use System)


(class Movie of Obj
	(properties
		scratch 0
		number 0
		x 0
		y 0
		client 0
		caller 0
		opened 0
		paused 0
		type $0001
	)
	
	(method (dispose)
		(= y (= x 0))
		(if client (client movie: 0))
		(if caller (caller cue:) (= caller 0))
	)
	
	(method (play param1 theCaller param3 param4 &tmp temp0)
		(if (and (> argc 1) theCaller) (= caller theCaller))
		(if (and (> argc 2) param3) (self posn: param3 param4))
		(if (not opened) (self open: param1))
		(= temp0
			(Str
				format:
				(switch type
					(0 {%d.mov})
					(1 {%d.avi})
				) number
			)
		)
		(ShowMovie 1 2 (temp0 data?))
		(temp0 dispose:)
		(self close: dispose:)
	)
	
	(method (open theNumber param2 param3 &tmp temp0)
		(= number theNumber)
		(= temp0
			(Str
				format:
				(switch type
					(0 {%d.mov})
					(1 {%d.avi})
				) number
			)
		)
		(ShowMovie 1 0 (temp0 data?))
		(if (and (> argc 1) param2) (self posn: param2 param3))
		(if (proc951_5 38)
			(ShowMovie 1 15 x y)
		else
			(ShowMovie 1 1 x y)
		)
		(= opened 1)
		(temp0 dispose:)
	)
	
	(method (close &tmp temp0)
		(= temp0
			(Str
				format:
				(switch type
					(0 {%d.mov})
					(1 {%d.avi})
				) number
			)
		)
		(ShowMovie 1 6 (temp0 data?))
		(= opened 0)
		(temp0 dispose:)
	)
	
	(method (posn theX theY)
		(= x theX)
		(= y theY)
	)
	
	(method (setPal param1 &tmp temp0)
		(= temp0 (Str format: {%d.pal} param1))
		(ShowMovie 1 7 (temp0 data?))
		(temp0 dispose:)
	)
	
	(method (putDouble theX theY)
		(if (and (> argc 1) theX) (= x theX) (= y theY))
		(ShowMovie 1 15 x y)
	)
	
	(method (put what recipient)
		(if (and (> argc 1) what) (= x what) (= y recipient))
		(ShowMovie 1 1 x y)
	)
	
	(method (pause)
		(if (not paused) (ShowMovie 1 4) (= paused 1))
	)
	
	(method (setWaitEvent param1 param2)
		(if (and (> argc 0) param1)
			(ShowMovie
				1
				14
				param1
				(if (and (> argc 1) param2) param2 else 0)
			)
		else
			(ShowMovie 1 14)
		)
	)
	
	(method (resume)
		(if paused (ShowMovie 1 5) (= paused 0))
	)
)

(class VmdMovie of Movie
	(properties
		scratch 0
		number 0
		x 0
		y 0
		client 0
		caller 0
		opened 0
		paused 0
		type $0002
		size 0
	)
	
	(method (play param1 param2)
		(if argc (PlayVMD 2 param1 param2) else (PlayVMD 2))
	)
	
	(method (open theNumber theSize theCaller &tmp temp0)
		(= number theNumber)
		(if (and (> argc 0) theSize)
			(= size theSize)
			(if (and (> argc 1) theCaller) (= caller theCaller))
		)
		(= temp0 (Str format: {%d.vmd} number))
		(PlayVMD 0 (temp0 data?) size)
		(temp0 dispose:)
		(= opened 1)
	)
	
	(method (close)
		(PlayVMD 6)
		(= opened 0)
	)
	
	(method (posn)
		(super posn: &rest)
		(PlayVMD 1 x y)
	)
	
	(method (setPal)
		(PlayVMD 7)
	)
	
	(method (putDouble)
	)
	
	(method (put what recipient param3 param4)
		(if (not number) (return))
		(if (and what recipient (> argc 0))
			(self posn: what recipient)
			(if (and (> argc 1) param3)
				(PlayVMD
					1
					x
					y
					param3
					(if (and (> argc 2) param4) param4 else 0)
				)
			)
		)
	)
	
	(method (setWaitEvent param1 param2)
		(if (and (> argc 0) param1)
			(PlayVMD
				14
				param1
				(if (and (> argc 1) param2) param2 else 0)
			)
		else
			(PlayVMD 14)
		)
	)
	
	(method (black param1 param2 param3 param4)
		(PlayVMD 21 param1 param2 param3 param4)
	)
	
	(method (getStatus)
		(PlayVMD 10)
	)
	
	(method (stop theNumber &tmp temp0)
		(if (and (> argc 0) theNumber) (= number theNumber))
		(= temp0 (Str format: {%d.vmd} number))
		(PlayVMD 3 (temp0 data?))
		(temp0 dispose:)
	)
	
	(method (showCursor)
		(PlayVMD 16 1)
	)
)
