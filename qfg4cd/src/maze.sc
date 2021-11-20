;;; Sierra Script 1.0 - (do not remove this comment)
(script# 81)
(include sci.sh)
(use Main)
(use TextIcon)
(use Array)
(use Motion)
(use Actor)
(use System)

(public
	maze 0
)

(local
	local0
	clientX
	clientY
	local3
	theAntMover_2
	theAntMover
	local6
	local7
	newIntArray
	newIntArray_2
	local10
	local11
	local12
	local13
	local14
	local15
	local16
	local17
	local18
	local19
	local20
	local21
	local22
	theDy
	theTheDy
	local25
	local26
	gCursorNumberView
	gCursorNumberLoop
	gCursorNumberCel
	newIntArray_3
	local31
)
(procedure (localproc_00b8 &tmp temp0 temp1 [temp2 2])
	(= local21 0)
	(ant hide:)
	(local7 at: 0 30687)
	(local7 at: 1 5460)
	(local7 at: 2 32119)
	(local7 at: 3 16705)
	(local7 at: 4 24437)
	(local7 at: 5 20741)
	(local7 at: 6 32223)
	(local7 at: 7 1104)
	(local7 at: 8 32637)
	(local7 at: 9 4357)
	(local7 at: 10 22391)
	(local7 at: 11 20561)
	(local7 at: 12 24031)
	(local7 at: 13 20800)
	(local7 at: 14 32639)
	(mazeIcon cel: 0)
	(= local16 14)
	(= local17 10)
	(= local10 14)
	(= local11 0)
	(mykey
		x: (localproc_0835 14)
		y: (localproc_084d 0)
		cel: 0
	)
	(newIntArray at: 0 4)
	(newIntArray_2 at: 0 2)
	(newIntArray at: 1 4)
	(newIntArray_2 at: 1 4)
	(newIntArray at: 2 6)
	(newIntArray_2 at: 2 6)
	(newIntArray at: 3 10)
	(newIntArray_2 at: 3 10)
	(newIntArray at: 4 10)
	(newIntArray_2 at: 4 14)
	(newIntArray at: 5 0)
	(newIntArray_2 at: 5 0)
	(newIntArray at: 6 8)
	(newIntArray_2 at: 6 0)
	(newIntArray at: 7 14)
	(newIntArray_2 at: 7 8)
	(newIntArray at: 8 12)
	(newIntArray_2 at: 8 10)
	(= theAntMover 0)
	(= theAntMover_2 14)
	(if (ant mover?) ((ant mover?) caller: 0))
	(= local26 1)
	(= temp1 0)
	(while (< temp1 local6)
		((newIntArray_3 at: temp1)
			x: (localproc_0841 (newIntArray at: temp1))
			y: (localproc_0859 (newIntArray_2 at: temp1))
		)
		(++ temp1)
	)
	(maze setScript: antOut)
)

(procedure (localproc_0309)
	(= local10 14)
	(= local11 0)
	(mykey
		x: (localproc_0835 14)
		y: (localproc_084d 0)
		setLoop: 9 1
		cel: 0
		show:
	)
	(= local16 14)
	(= local17 10)
	(newIntArray at: 0 4)
	(newIntArray_2 at: 0 2)
	(newIntArray at: 1 4)
	(newIntArray_2 at: 1 4)
	(newIntArray at: 2 6)
	(newIntArray_2 at: 2 6)
	(newIntArray at: 3 10)
	(newIntArray_2 at: 3 10)
	(newIntArray at: 4 10)
	(newIntArray_2 at: 4 14)
	(newIntArray at: 5 0)
	(newIntArray_2 at: 5 0)
	(newIntArray at: 6 8)
	(newIntArray_2 at: 6 0)
	(newIntArray at: 7 14)
	(newIntArray_2 at: 7 8)
	(newIntArray at: 8 12)
	(newIntArray_2 at: 8 10)
	(= theAntMover 0)
	(= theAntMover_2 14)
)

(procedure (localproc_0419)
	(mykey cel: (mazeIcon cel?))
	(switch (mazeIcon cel?)
		(0
			(local7 at: 0 30687)
			(local7 at: 1 5460)
			(local7 at: 2 32119)
			(local7 at: 3 16705)
			(local7 at: 4 24437)
			(local7 at: 5 20741)
			(local7 at: 6 32223)
			(local7 at: 7 1104)
			(local7 at: 8 32637)
			(local7 at: 9 4357)
			(local7 at: 10 22391)
			(local7 at: 11 20561)
			(local7 at: 12 24031)
			(local7 at: 13 20800)
			(local7 at: 14 32639)
		)
		(1
			(local7 at: 0 24445)
			(local7 at: 1 20757)
			(local7 at: 2 30581)
			(local7 at: 3 16709)
			(local7 at: 4 30173)
			(local7 at: 5 5201)
			(local7 at: 6 32223)
			(local7 at: 7 16644)
			(local7 at: 8 32631)
			(local7 at: 9 17489)
			(local7 at: 10 30165)
			(local7 at: 11 5445)
			(local7 at: 12 30591)
			(local7 at: 13 20801)
			(local7 at: 14 24415)
		)
		(2
			(local7 at: 0 32639)
			(local7 at: 1 325)
			(local7 at: 2 32221)
			(local7 at: 3 17669)
			(local7 at: 4 30581)
			(local7 at: 5 20549)
			(local7 at: 6 24447)
			(local7 at: 7 1296)
			(local7 at: 8 32223)
			(local7 at: 9 20549)
			(local7 at: 10 22397)
			(local7 at: 11 16705)
			(local7 at: 12 30559)
			(local7 at: 13 5460)
			(local7 at: 14 32247)
		)
		(else 
			(local7 at: 0 32125)
			(local7 at: 1 16709)
			(local7 at: 2 32631)
			(local7 at: 3 20820)
			(local7 at: 4 21975)
			(local7 at: 5 17681)
			(local7 at: 6 30591)
			(local7 at: 7 4161)
			(local7 at: 8 32223)
			(local7 at: 9 17684)
			(local7 at: 10 24023)
			(local7 at: 11 20801)
			(local7 at: 12 22391)
			(local7 at: 13 21573)
			(local7 at: 14 24445)
		)
	)
)

(procedure (localproc_0756 param1 &tmp temp0 temp1)
	(if param1
		(= temp0 0)
		(while (< temp0 local6)
			(= temp1 (newIntArray at: temp0))
			(newIntArray at: temp0 (newIntArray_2 at: temp0))
			(newIntArray_2 at: temp0 (- 14 temp1))
			(++ temp0)
		)
		(= temp1 local10)
		(= local10 local11)
		(= local11 (- 14 temp1))
		(= temp1 local16)
		(= local16 local17)
		(= local17 (- 14 temp1))
	else
		(= temp0 0)
		(while (< temp0 local6)
			(= temp1 (newIntArray_2 at: temp0))
			(newIntArray_2 at: temp0 (newIntArray at: temp0))
			(newIntArray at: temp0 (- 14 temp1))
			(++ temp0)
		)
		(= temp1 local11)
		(= local11 local10)
		(= local10 (- 14 temp1))
		(= temp1 local17)
		(= local17 local16)
		(= local16 (- 14 temp1))
	)
	(mykey
		x: (localproc_0835 local10)
		y: (localproc_084d local11)
	)
)

(procedure (localproc_0835 param1)
	(return (+ 31 (* 14 (/ param1 2))))
)

(procedure (localproc_0841 param1)
	(return (+ 29 (* 14 (/ param1 2))))
)

(procedure (localproc_084d param1)
	(return (+ 36 (* 10 (/ param1 2))))
)

(procedure (localproc_0859 param1)
	(return (+ 33 (* 10 (/ param1 2))))
)

(procedure (localproc_0865 &tmp temp0 temp1 temp2)
	(= theTheDy 0)
	(= temp1 0)
	(while
		(and
			(>= (= temp0 (- theAntMover_2 (++ temp1))) 0)
			(& (local7 at: theAntMover) (<< $0001 temp0))
		)
	)
	(= temp2 0)
	(while
		(and
			(< (= temp0 (+ theAntMover_2 (++ temp2))) 15)
			(& (local7 at: theAntMover) (<< $0001 temp0))
		)
	)
	(return
		(if (== temp1 0)
			(return 0)
		else
			(if (> temp1 6) (= temp1 4))
			(if (== (/ temp1 2) 1)
				(= theTheDy -3)
			else
				(= theTheDy -4)
			)
			(return (* (/ temp1 2) 9))
		)
	)
)

(procedure (localproc_08ec &tmp temp0)
	(= local13 0)
	(while
		(and
			(< (= temp0 (+ theAntMover_2 (++ local13))) 15)
			(& (local7 at: theAntMover) (<< $0001 temp0))
		)
	)
	(return
		(if (-- local13)
			(return 0)
		else
			(return (localproc_084d theAntMover_2))
		)
	)
)

(procedure (localproc_0926 &tmp [temp0 2] temp2 temp3 temp4 temp5)
	(asm
		ldi      0
		sal      local21
		ldi      0
		sal      local15
code_0930:
		lsl      theAntMover_2
		+al      local15
		add     
		sat      temp2
		push    
		ldi      15
		lt?     
		bnt      code_09a2
		pushi    #at
		pushi    1
		lsl      theAntMover
		lal      local7
		send     6
		push    
		pushi    1
		lat      temp2
		shl     
		and     
		bnt      code_09a2
		lsl      theAntMover
		lal      local10
		eq?     
		bnt      code_0968
		lsl      theAntMover_2
		lal      local15
		add     
		push    
		lal      local11
		eq?     
		bnt      code_0968
		ldi      1
		sal      local20
		ldi      2
		ret     
code_0968:
		ldi      0
		sat      temp3
code_096c:
		lst      temp3
		lal      local6
		lt?     
		bnt      code_0930
		lsl      theAntMover
		pushi    #at
		pushi    1
		lst      temp3
		lal      newIntArray
		send     6
		eq?     
		bnt      code_099c
		lsl      theAntMover_2
		lal      local15
		add     
		push    
		pushi    #at
		pushi    1
		lst      temp3
		lal      newIntArray_2
		send     6
		eq?     
		bnt      code_099c
		ldi      1
		sal      local21
		ldi      1
		ret     
code_099c:
		+at      temp3
		jmp      code_096c
		jmp      code_0930
code_09a2:
		ldi      0
		sal      local15
		ldi      0
		sal      local14
		pushi    0
		call     localproc_08ec,  0
		lal      local13
		bnt      code_09bb
		lsl      theAntMover_2
		add     
		sat      temp4
		jmp      code_09bf
code_09bb:
		lal      theAntMover_2
		sat      temp4
code_09bf:
		lsl      local14
		ldi      4
		lt?     
		bnt      code_0a3a
		lst      temp4
		+al      local14
		sub     
		sat      temp2
		push    
		ldi      0
		ge?     
		bnt      code_0a3a
		pushi    #at
		pushi    1
		lsl      theAntMover
		lal      local7
		send     6
		push    
		pushi    1
		lat      temp2
		shl     
		and     
		bnt      code_0a3a
		lsl      theAntMover
		lal      local10
		eq?     
		bnt      code_0a00
		lst      temp4
		lal      local14
		sub     
		push    
		lal      local11
		eq?     
		bnt      code_0a00
		ldi      1
		sal      local20
		ldi      2
		ret     
code_0a00:
		ldi      0
		sat      temp3
code_0a04:
		lst      temp3
		lal      local6
		lt?     
		bnt      code_09bf
		lsl      theAntMover
		pushi    #at
		pushi    1
		lst      temp3
		lal      newIntArray
		send     6
		eq?     
		bnt      code_0a34
		lst      temp4
		lal      local14
		sub     
		push    
		pushi    #at
		pushi    1
		lst      temp3
		lal      newIntArray_2
		send     6
		eq?     
		bnt      code_0a34
		ldi      1
		sal      local21
		ldi      1
		ret     
code_0a34:
		+at      temp3
		jmp      code_0a04
		jmp      code_09bf
code_0a3a:
		ldi      0
		ret     
	)
)

(instance maze of PuzzleBar
	(properties)
	
	(method (init &tmp temp0)
		(= local31 1)
		(= gCursorNumberView (theCursor view?))
		(= gCursorNumberLoop (theCursor loop?))
		(= gCursorNumberCel (theCursor cel?))
		(theGame
			setCursor: ((theIconBar getCursor:)
				view: 999
				loop: 0
				cel: 0
				yourself:
			)
		)
		(= newIntArray (IntArray new:))
		(= newIntArray_2 (IntArray new:))
		(= newIntArray_3 (IntArray new:))
		(= local7
			(IntArray
				with:
					30687
					5460
					32119
					16705
					24437
					20741
					32223
					1104
					32637
					4357
					22391
					20561
					24031
					20800
					32639
			)
		)
		(localproc_0309)
		(super init:)
		(puzzleCast
			add: (ant init: self yourself:) (mykey init: self yourself:)
		)
		(= local6
			(switch arcadeLevel
				(3 9)
				(2 5)
				(else  0)
			))
		(= temp0 0)
		(while (< temp0 local6)
			(newIntArray_3
				at:
					temp0
					((holeIcon new:)
						x: (localproc_0841 (newIntArray at: temp0))
						y: (localproc_0859 (newIntArray_2 at: temp0))
						init: self
						yourself:
					)
			)
			(puzzleCast add: (newIntArray_3 at: temp0))
			(++ temp0)
		)
		(self
			add: mazeIcon leftSide rightSide bottomSide rotateL rotateR
		)
		(self eachElementDo: #init self)
	)
	
	(method (dispose)
		(local7 dispose:)
		(newIntArray dispose:)
		(newIntArray_2 dispose:)
		(newIntArray_3 dispose:)
		(theGame
			setCursor:
				((theIconBar getCursor:)
					view: gCursorNumberView
					loop: gCursorNumberLoop
					cel: gCursorNumberCel
					yourself:
				)
		)
		(super dispose:)
	)
	
	(method (hide)
		(if local18
			(if (not (ego has: 58))
				(ego solvePuzzle: 409 6 get: 58)
			)
			(messager say: 18 6 20)
		)
		(super hide: &rest)
	)
	
	(method (resetPuzzle)
		(= local18 0)
		(= local12 0)
		(= local20 0)
		(self setScript: antOut)
	)
	
	(method (helpYou)
		(messager say: 18 9 0 local31)
		(if (< local31 5)
			(++ local31)
		else
			(ego get: 58)
			(self hide:)
		)
	)
	
	(method (giveYou)
		(messager say: 21 4 24)
	)
	
	(method (setPlane)
		(= usePlane 1)
		(plane bitmap: 0)
		(plane
			priority: (+ (GetHighPlanePri) 1)
			init:
			setRect: 82 40 250 182
			setBitmap: 935 0 0 1
			addCast: puzzleCast
		)
	)
)

(instance antOut of Script
	(properties)
	
	(method (dispose)
		(maze noHands: 0)
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(maze noHands: 1)
				(cond 
					(local26 (= local26 0) (messager say: 18 6 29 0 self))
					((not (Btst 409)) (messager say: 18 6 19 0 self))
					(else (= ticks 1))
				)
			)
			(1 (= seconds 2))
			(2
				(ant
					x: (= temp1 (localproc_0835 theAntMover))
					y: (= temp2 (+ (localproc_084d theAntMover_2) 1))
					setLoop: 12 1
					cel: 0
					cycleSpeed: 12
					setCycle: End self
					show:
				)
			)
			(3
				(= temp0 (localproc_0865))
				(ant
					x: (= temp1 (localproc_0835 theAntMover))
					y: (= temp2 (localproc_084d theAntMover_2))
					setLoop: 1 1
					cel: 0
					moveSpeed: (/ (+ (ego moveSpeed?) 1) 2)
					setMotion: (bounceTo new:) temp1 (- (- temp2 temp0) 2)
				)
				(self dispose:)
			)
		)
	)
)

(instance ant of Actor
	(properties
		priority 254
		fixPriority 1
		view 382
		loop 1
		yStep 1
		signal $4001
		illegalBits $0000
	)
	
	(method (onMe)
		(return 0)
	)
	
	(method (cue)
		(maze noHands: 0)
		(= theAntMover_2 (+ theAntMover_2 local13))
		(self
			moveSpeed: (/ (+ (ego moveSpeed?) 1) 2)
			setCycle: 0
			setMotion: (bounceTo new:) x (- (- y (localproc_0865)) 2)
		)
	)
	
	(method (setMotion)
		(if theTheDy (= theDy theTheDy))
		(self setLoop: 1 1)
		(ant cel: 0)
		(super setMotion: &rest)
	)
)

(instance mykey of View
	(properties
		priority 254
		fixPriority 1
		view 382
		loop 9
		signal $4000
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance holeIcon of View
	(properties
		priority 254
		fixPriority 1
		view 382
		loop 8
		signal $4000
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance mazeIcon of TextIcon
	(properties
		priority 189
		fixPriority 1
		view 382
		cel 0
	)
	
	(method (init)
		(= nsLeft (= x (+ (- 77 (/ (CelWide 382 0 0) 2)) 3)))
		(= nsTop (= y 30))
		(super init: &rest)
	)
	
	(method (onMe)
		(return 0)
	)
	
	(method (select)
	)
	
	(method (highlight)
	)
)

(instance rotateL of TextIcon
	(properties
		view 382
		loop 3
	)
	
	(method (init)
		(= nsLeft (= x 8))
		(= nsTop (= y (- (+ 30 (CelHigh 382 0 0)) 12)))
		(super init: &rest)
	)
	
	(method (select &tmp antMover temp1 temp2 temp3)
		(if (super select: &rest)
			(if (< (+ (mazeIcon cel?) 1) 4)
				(mazeIcon cel: (+ (mazeIcon cel?) 1))
			else
				(mazeIcon cel: 0)
			)
			(if (= antMover (ant mover?))
				(if
				(and (> (- (antMover y?) clientY) 0) (!= local19 0))
					(= theAntMover_2
						(-
							theAntMover_2
							(* (/ (- (antMover y?) (ant y?)) 8) 2)
						)
					)
				else
					(= theAntMover_2
						(- theAntMover_2 (* (/ (- clientY (ant y?)) 8) 2))
					)
				)
			)
			(localproc_0419)
			(localproc_0756 1)
			(= temp3 0)
			(while (< temp3 local6)
				((newIntArray_3 at: temp3)
					x: (localproc_0841 (newIntArray at: temp3))
					y: (localproc_0859 (newIntArray_2 at: temp3))
				)
				(++ temp3)
			)
			(= antMover theAntMover)
			(= theAntMover theAntMover_2)
			(= theAntMover_2 (- 14 antMover))
			(localproc_0926)
			(= temp1 (localproc_0835 theAntMover))
			(if (not (= temp2 (localproc_08ec theAntMover_2)))
				(= temp2 (localproc_084d theAntMover_2))
				(ant
					x: temp1
					y: temp2
					setCycle: 0
					setMotion:
						(myMoveTo new:)
						temp1
						(localproc_084d (+ theAntMover_2 local13))
						ant
				)
			else
				(= antMover (localproc_0865))
				(ant x: temp1 y: temp2)
				(cond 
					(antMover
						(ant
							moveSpeed: (/ (+ (ego moveSpeed?) 1) 2)
							setCycle: 0
							setMotion: (bounceTo new:) temp1 (- (- temp2 antMover) 2)
						)
					)
					(
						(and
							local12
							(== theAntMover local16)
							(== theAntMover_2 local17)
						)
						(ant
							setCycle: 0
							setMotion:
								goOut
								temp1
								(localproc_084d
									(if (> (+ theAntMover_2 2) 14)
										(+ theAntMover_2 2)
									else
										(- theAntMover_2 2)
									)
								)
						)
					)
					(else
						(ant
							y: (+ (ant y?) 1)
							setMotion: 0
							setLoop: 13 1
							cycleSpeed: 6
							setCycle: Fwd
						)
					)
				)
			)
		)
	)
)

(instance rotateR of TextIcon
	(properties
		view 382
		loop 2
	)
	
	(method (init)
		(= nsLeft
			(= x
				(-
					(-
						154
						(CelWide
							(rotateR view?)
							(rotateR loop?)
							(rotateR cel?)
						)
					)
					2
				)
			)
		)
		(= nsTop (= y (- (+ 30 (CelHigh 382 0 0)) 12)))
		(super init: &rest)
	)
	
	(method (select &tmp antMover temp1 temp2 temp3)
		(if (super select: &rest)
			(if (> (- (mazeIcon cel?) 1) -1)
				(mazeIcon cel: (- (mazeIcon cel?) 1))
			else
				(mazeIcon cel: 3)
			)
			(if (= antMover (ant mover?))
				(if
				(and (> (- (antMover y?) clientY) 0) (!= local19 0))
					(= theAntMover_2
						(-
							theAntMover_2
							(* (/ (- (antMover y?) (ant y?)) 8) 2)
						)
					)
				else
					(= theAntMover_2
						(- theAntMover_2 (* (/ (- clientY (ant y?)) 8) 2))
					)
				)
			)
			(localproc_0419)
			(localproc_0756 0)
			(= temp3 0)
			(while (< temp3 local6)
				((newIntArray_3 at: temp3)
					x: (localproc_0841 (newIntArray at: temp3))
					y: (localproc_0859 (newIntArray_2 at: temp3))
				)
				(++ temp3)
			)
			(= antMover theAntMover_2)
			(= theAntMover_2 theAntMover)
			(= theAntMover (- 14 antMover))
			(localproc_0926)
			(= temp1 (localproc_0835 theAntMover))
			(if (not (= temp2 (localproc_08ec theAntMover_2)))
				(= temp2 (localproc_084d theAntMover_2))
				(ant
					x: temp1
					y: temp2
					setCycle: 0
					setMotion:
						(myMoveTo new:)
						temp1
						(localproc_084d (+ theAntMover_2 local13))
						ant
				)
			else
				(= antMover (localproc_0865))
				(ant x: temp1 y: temp2 cel: 0)
				(cond 
					(antMover
						(ant
							moveSpeed: (/ (+ (ego moveSpeed?) 1) 2)
							setCycle: 0
							setMotion: (bounceTo new:) temp1 (- (- temp2 antMover) 2)
						)
					)
					(
						(and
							local12
							(== theAntMover local16)
							(== theAntMover_2 local17)
						)
						(ant
							setCycle: 0
							setMotion:
								goOut
								temp1
								(localproc_084d
									(if (> (+ theAntMover_2 2) 14)
										(+ theAntMover_2 2)
									else
										(- theAntMover_2 2)
									)
								)
						)
					)
					(else
						(ant
							y: (+ (ant y?) 1)
							setMotion: 0
							setLoop: 13 1
							cycleSpeed: 6
							setCycle: Fwd
						)
					)
				)
			)
		)
	)
)

(instance leftSide of TextIcon
	(properties
		x 8
		y 30
		view 382
		loop 4
		cel 0
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance rightSide of TextIcon
	(properties
		x 140
		y 30
		view 382
		loop 4
		cel 0
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance bottomSide of TextIcon
	(properties
		view 382
		loop 4
		cel 1
	)
	
	(method (init)
		(= x (+ (- 77 (/ (CelWide 382 4 1) 2)) 3))
		(= y (+ 35 (CelHigh 382 0 0)))
		(super init: &rest)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance myMoveTo of MoveTo
	(properties)
	
	(method (init)
		(maze noHands: 1)
		(super init: &rest)
		(= local22 (^ local22 local22))
		(= clientX (client x?))
		(= clientY (client y?))
		(= local19 0)
	)
	
	(method (doit)
		(if (> (++ local22) 3)
			(= local22 0)
			(if (> clientY y) (if dy (-- dy)) else (++ dy))
		)
		(super doit:)
	)
	
	(method (moveDone)
		(cond 
			(local20 (= local12 1) (mykey hide:))
			((and local21 local15) (= caller 0) (localproc_00b8))
		)
		(super moveDone: &rest)
	)
)

(instance bounceTo of MoveTo
	(properties)
	
	(method (init)
		(super init: &rest)
		(client cel: 0)
		(if theDy (= dy theDy))
		(= local22 (^ local22 local22))
		(= clientX (client x?))
		(= clientY (client y?))
		(= local19 1)
	)
	
	(method (doit)
		(if (> (++ local22) 3)
			(= local22 0)
			(if (+ dy 1) (++ dy))
		)
		(super doit:)
	)
	
	(method (moveDone)
		(if local21
			(super moveDone: &rest)
			(localproc_00b8)
		else
			(cond 
				((> clientY y) (= theDy (- 0 dy)))
				(theTheDy (= theDy theTheDy))
			)
			(self init: client clientX clientY)
		)
	)
)

(instance bounceCycle of CT
	(properties)
	
	(method (doit &tmp cTNextCel)
		(if (> (= cTNextCel (self nextCel:)) 4)
			(self cycleDone:)
		else
			(client cel: cTNextCel)
		)
	)
	
	(method (motionCue)
		(super motionCue:)
		(client setLoop: 1 1 cel: 0)
		((client mover?) init: client clientX clientY)
	)
)

(instance goOut of MoveTo
	(properties)
	
	(method (init)
		(maze noHands: 1)
		(super init: &rest)
		(= local19 3)
	)
	
	(method (moveDone)
		(super moveDone: &rest)
		(client hide:)
		(= local18 1)
		(maze state: (& (maze state?) $ffdf))
	)
)
