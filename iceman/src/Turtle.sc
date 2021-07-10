;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40)
(include sci.sh)
(use Main)
(use Intrface)
(use subMarine)
(use n802)
(use Submarine_806)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	chartRm 0
)

(local
	egoX
	egoY
	egoView
	egoLoop
	[local4 20]
	[local24 27]
	local51 =  5
	egoX_2
	egoY_2
	local54
	local55
	[local56 5]
	local61 =  160
	local62 =  89
	local63 =  160
	local64 =  89
	local65 =  285
	local66 =  77
	[local67 28] = [0 15 30 45 60 75 90 105 120 130 135 140 145 150 155 141 129 116 104 94 83 69 55 41 37 28 16 4]
	[local95 42] = [0 15 30 45 60 75 90 105 120 135 150 165 180 195 210 225 235 285 315 316 298 282 270 260 250 235 223 209 195 180 165 155 143 128 118 106 67 37]
	egoX_3
	egoY_3
	local139
	local140
	local141
	local142
	[local143 12] = [151 83 151 39 226 12 301 19 274 48 285 77]
	[local155 14]
	local169
	local170
)
(procedure (localproc_09b8 param1 &tmp temp0 temp1)
	(= temp0 (= temp1 0))
	(while
		(and
			(< [local95 37] param1)
			(< param1 [local95 (+ 19 temp1)])
		)
		(= temp0 temp1)
		(++ temp1)
	)
	(return
		(if
			(<
				195
				(= temp0
					(+
						[local95 temp0]
						(/
							(*
								(-
									param1
									[local95 (+ (if (== temp0 temp1) (return 777)) 19)]
								)
								(- [local95 temp1] [local95 temp0])
							)
							(- [local95 (+ temp1 19)] [local95 (+ temp0 19)])
						)
					)
				)
			)
			(return (- temp0 375))
		else
			(return (- temp0 15))
		)
	)
)

(procedure (localproc_0a2f param1 &tmp temp0 temp1)
	(= temp0 (= temp1 0))
	(while
	(and (< [local95 temp1] param1) (< param1 [local95 18]))
		(= temp0 temp1)
		(++ temp1)
	)
	(if (== temp0 temp1) (return 777))
	(return
		(+
			[local95 (+ temp0 19)]
			(/
				(*
					(- param1 [local95 temp0])
					(- [local95 (+ temp1 19)] [local95 (+ temp0 19)])
				)
				(- [local95 temp1] [local95 temp0])
			)
		)
	)
)

(procedure (localproc_0a8b param1 &tmp temp0 temp1)
	(= temp0 (= temp1 0))
	(while
		(and
			(<= [local67 27] param1)
			(<= param1 [local67 (+ 14 temp1)])
		)
		(= temp0 temp1)
		(++ temp1)
	)
	(return
		(-
			(= temp0
				(+
					[local67 temp0]
					(/
						(*
							(-
								param1
								[local67 (+ (if (== temp0 temp1) (return 777)) 14)]
							)
							(- [local67 temp1] [local67 temp0])
						)
						(- [local67 (+ temp1 14)] [local67 (+ temp0 14)])
					)
				)
			)
			60
		)
	)
)

(procedure (localproc_0aef param1 &tmp temp0 temp1)
	(= temp0 (= temp1 0))
	(while
	(and (< [local67 temp1] param1) (< param1 [local67 13]))
		(= temp0 temp1)
		(++ temp1)
	)
	(if (== temp0 temp1) (return 777))
	(return
		(+
			[local67 (+ temp0 14)]
			(/
				(*
					(- param1 [local67 temp0])
					(- [local67 (+ temp1 14)] [local67 (+ temp0 14)])
				)
				(- [local67 temp1] [local67 temp0])
			)
		)
	)
)

(procedure (localproc_0b6d &tmp temp0)
	(if (<= (markerList size?) 1) (return))
	(if
	(IsObject (= temp0 (NodeValue (markerList first:))))
		(courseMarker posn: (temp0 x?) (temp0 y?))
		(if
			(IsObject
				(= temp0
					(NodeValue (markerList next: (markerList first:)))
				)
			)
			(courseMarker
				penColor: 7
				setMotion: MoveTo (temp0 x?) (temp0 y?) temp0
			)
		)
	)
)

(procedure (localproc_0bf5 &tmp temp0)
	(= temp0 0)
	(while (< temp0 7)
		(if (> (markerList size?) temp0)
			(Format
				@local4
				40
				12
				(Abs (localproc_0a8b ((markerList at: temp0) y?)))
				(Abs (localproc_09b8 ((markerList at: temp0) x?)))
			)
		else
			(Format @local4 40 12 0 0)
		)
		(Display
			@local4
			dsCOORD
			0
			local51
			dsBACKGROUND
			0
			dsCOLOR
			15
			dsFONT
			30
		)
		(= local51 (+ local51 15))
		(++ temp0)
	)
	(= local51 11)
)

(procedure (localproc_0eda param1 &tmp markerListFirst temp1)
	(if
		(and
			(= markerListFirst (markerList first:))
			(IsObject (= temp1 (NodeValue markerListFirst)))
		)
		(Submarine param1: (temp1 x?))
	)
)

(procedure (localproc_0f08 param1 &tmp markerListFirst temp1)
	(if
		(and
			(= markerListFirst (markerList first:))
			(IsObject (= temp1 (NodeValue markerListFirst)))
		)
		(Submarine param1: (temp1 y?))
		(markerList delete: temp1)
	)
)

(procedure (localproc_0f40 param1 param2 param3)
	(OnControl
		4
		(- param1 param3)
		(- param2 param3)
		(+ param1 param3)
		(+ param2 param3)
	)
)

(procedure (localproc_1458 &tmp temp0 temp1)
	(localproc_15ec)
	(= local169 0)
	(= temp0 0)
	(while (and (< temp0 14) (!= [local155 temp0] 0))
		(if
			(<
				local169
				(= temp1
					(localproc_14b9 [local155 temp0] [local155 (++ temp0)])
				)
			)
			(= local169 temp1)
		)
		(++ temp0)
	)
	(Printf 40 22 (* 50 local169))
	(return (if (< 12 local169) (return 0) else (return 1)))
)

(procedure (localproc_14b9 param1 param2 &tmp temp0 temp1 temp2)
	(asm
		ldi      90
		sat      temp1
		ldi      0
		sat      temp0
code_14c3:
		lst      temp0
		ldi      10
		lt?     
		bnt      code_14f7
		pushi    6
		lat      temp0
		lsli     local143
		+at      temp0
		lsli     local143
		+at      temp0
		lsli     local143
		push    
		ldi      1
		add     
		lsli     local143
		lsp      param1
		lsp      param2
		call     localproc_14fa,  12
		sat      temp2
		push    
		lat      temp1
		lt?     
		bnt      code_14c3
		lat      temp2
		sat      temp1
		jmp      code_14c3
code_14f7:
		lat      temp1
		ret     
	)
)

(procedure (localproc_14fa param1 param2 param3 param4 param5 param6)
	(return
		(if
			(and
				(<=
					0
					(localproc_159e
						(- param3 param1)
						(- param4 param2)
						(- param5 param1)
						(- param6 param2)
					)
				)
				(<=
					0
					(localproc_159e
						(- param1 param3)
						(- param2 param4)
						(- param5 param3)
						(- param6 param4)
					)
				)
			)
			(return
				(/
					(Abs
						(localproc_159e
							(- param4 param2)
							(- param1 param3)
							(- param5 param1)
							(- param6 param2)
						)
					)
					(localproc_15ab param1 param2 param3 param4)
				)
			)
		else
			(return
				(Min
					(localproc_15ab param5 param6 param1 param2)
					(localproc_15ab param5 param6 param3 param4)
				)
			)
		)
	)
)

(procedure (localproc_159e param1 param2 param3 param4)
	(return (+ (* param1 param3) (* param2 param4)))
)

(procedure (localproc_15ab param1 param2 param3 param4 &tmp temp0 temp1)
	(if
		(or
			(< 90 (= temp0 (Abs (- param3 param1))))
			(< 90 (= temp1 (Abs (- param4 param2))))
		)
		90
	else
		(Sqrt
			(+
				(= temp0 (* temp0 temp0))
				(= temp1 (* temp1 temp1))
			)
		)
	)
)

(procedure (localproc_15ec &tmp temp0 markerListFirst temp2)
	(= temp0 0)
	(if
		(IsObject
			(= temp2
				(NodeValue (= markerListFirst (markerList first:)))
			)
		)
		(while (< temp0 14)
			(= [local155 temp0] (temp2 x?))
			(++ temp0)
			(if (< 90 (= [local155 temp0] (temp2 y?)))
				(= local170 1)
			)
			(if
				(IsObject
					(= temp2
						(NodeValue
							(= markerListFirst (markerList next: markerListFirst))
						)
					)
				)
				(++ temp0)
			else
				(= temp0 14)
			)
		)
	)
)

(instance chartRm of Rm
	(properties
		picture 40
		north 25
	)
	
	(method (init &tmp temp0)
		(= useSortedFeatures 0)
		(= egoX (ego x?))
		(= egoY (ego y?))
		(= egoView (ego view?))
		(= egoLoop (ego loop?))
		(super init:)
		(self
			setFeatures: mapArea
			setRegions: 314
			setScript: delayedInit
		)
		(locationMarker init:)
		(courseMarker init: penDown: 1)
		(User canControl: 0)
		(keyDownHandler addToFront: self)
		(directionHandler addToFront: self)
		(= local63 (localproc_0a2f 185))
		(= local64 (localproc_0aef 90))
		(= local61
			(localproc_0a2f (+ (Submarine longitude?) 15))
		)
		(= local62
			(localproc_0aef (+ (Submarine latitude?) 60))
		)
		(ego
			view: 40
			setLoop: 4
			cel: 0
			xStep: 1
			yStep: 1
			posn: local63 local64
			ignoreActors: 1
			init:
			setScript: stealMouse
		)
		(subMarker x: local61 y: local62 init: setCycle: Fwd)
		(destMarker x: local65 y: local66 init: setCycle: Fwd)
		(markerList
			addToEnd:
				((locationMarker new:)
					view: 40
					loop: 1
					x: local63
					y: local64
					init:
					setCycle: Fwd
					yourself:
				)
		)
		(self replay:)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(keyDownHandler delete: self)
		(= useSortedFeatures 1)
		(theGame setCursor: normalCursor 1)
		(markerList release: dispose:)
		(ego
			view: egoView
			loop: egoLoop
			x: egoX
			y: egoY
			xStep: 3
			yStep: 2
		)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<!*][/map,table,chart]')
				(Print 40 0)
				(Print 40 1)
				(Print 40 2)
				(Print 40 3)
				(Print 40 4)
			)
			((Said 'exit,exit,cease,stand')
				(if
					(or
						local142
						(!= local61 (localproc_0a2f 185))
						(!= local62 (localproc_0aef 90))
					)
					(localproc_0eda 374)
					(localproc_0f08 375)
					(localproc_0eda 374)
					(localproc_0f08 375)
					(localproc_0eda 376)
					(localproc_0f08 377)
					(localproc_0eda 378)
					(localproc_0f08 379)
					(localproc_0eda 380)
					(localproc_0f08 381)
					(localproc_0eda 382)
					(localproc_0f08 383)
					(self newRoom: 25)
				else
					(Print 40 5)
					(Print 40 6)
				)
			)
		)
	)
	
	(method (replay &tmp temp0)
		(= local55 local54)
		(= local54 0)
		(= local51 5)
		(= temp0 0)
		(while (<= temp0 6)
			(Format @local4 40 7 temp0)
			(Display @local4 dsCOORD 0 local51 dsCOLOR 10 dsFONT 30)
			(= local51 (+ local51 15))
			(++ temp0)
		)
		(= local51 11)
		(Display 40 8 dsCOORD 100 172 dsCOLOR 10 dsFONT 104)
		(if (Submarine wayPoint1X?)
			(= local142 1)
			(markerList
				addToEnd:
					((locationMarker new:)
						x: (Submarine wayPoint1X?)
						y: (Submarine wayPoint1Y?)
						init:
						setCycle: Fwd
						yourself:
					)
			)
			(Submarine wayPoint1X: 0)
		)
		(if (Submarine wayPoint2X?)
			(markerList
				addToEnd:
					((locationMarker new:)
						x: (Submarine wayPoint2X?)
						y: (Submarine wayPoint2Y?)
						init:
						setCycle: Fwd
						yourself:
					)
			)
			(Submarine wayPoint2X: 0)
		)
		(if (Submarine wayPoint3X?)
			(markerList
				addToEnd:
					((locationMarker new:)
						x: (Submarine wayPoint3X?)
						y: (Submarine wayPoint3Y?)
						init:
						setCycle: Fwd
						yourself:
					)
			)
			(Submarine wayPoint3X: 0)
		)
		(if (Submarine wayPoint4X?)
			(markerList
				addToEnd:
					((locationMarker new:)
						x: (Submarine wayPoint4X?)
						y: (Submarine wayPoint4Y?)
						init:
						setCycle: Fwd
						yourself:
					)
			)
			(Submarine wayPoint4X: 0)
		)
		(if (Submarine wayPoint5X?)
			(markerList
				addToEnd:
					((locationMarker new:)
						x: (Submarine wayPoint5X?)
						y: (Submarine wayPoint5Y?)
						init:
						setCycle: Fwd
						yourself:
					)
			)
			(Submarine wayPoint5X: 0)
		)
		(if (Submarine wayPoint1X?)
			(markerList
				addToEnd:
					((locationMarker new:)
						view: 40
						loop: 2
						x: local65
						y: local66
						init:
						setCycle: Fwd
						yourself:
					)
			)
		)
		(if local142 (localproc_1458))
		(localproc_0bf5)
		(localproc_0b6d)
		(= egoY_2 (= egoX_2 0))
	)
)

(instance delayedInit of Script
	(properties
		cycles 5
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(theGame setCursor: 40 0 330 200)
				(if (== ((subMarine script?) state?) 3)
					((subMarine script?) cycles: 0)
					(subMarine cue: 1)
				)
				(self dispose:)
			)
		)
	)
)

(instance mapArea of RFeature
	(properties
		y 80
		x 139
		nsTop 4
		nsLeft 37
		nsBottom 165
		nsRight 316
	)
	
	(method (handleEvent event &tmp temp0)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said
						'draw,draw,adjust,draw,chart,enter/course,coordinate'
					)
					(and
						(not local54)
						(Said 'alter,change/course,coordinate,coordinate')
					)
				)
				(if
					(and
						(== local61 (localproc_0a2f 185))
						(== local62 (localproc_0aef 90))
					)
					(if (== (chartRm script?) startPlot)
						(Print 40 9)
					else
						(if local142
							(theGame
								changeScore:
									(cond 
										((< local169 2) -10)
										((< local169 3) -5)
										((< local169 5) -4)
										((< local169 8) -3)
										((< local169 11) -2)
										(else -1)
									)
							)
						)
						(= local142 0)
						(chartRm setScript: startPlot)
					)
				else
					(Print 40 10)
				)
			)
		)
	)
)

(instance markerList of List
	(properties)
)

(instance locationMarker of Prop
	(properties
		view 40
		loop 1
	)
	
	(method (cue &tmp temp0)
		(cond 
			(
				(IsObject
					(= temp0
						(markerList at: (+ (markerList indexOf: self) 1))
					)
				)
				(if (> (courseMarker distanceTo: temp0) 1)
					(courseMarker
						setMotion: MoveTo (temp0 x?) (temp0 y?) temp0
					)
				)
			)
			(local55 (= local54 local55) (= local55 0))
		)
	)
)

(instance subMarker of Prop
	(properties
		view 40
		loop 3
	)
)

(instance destMarker of Prop
	(properties
		view 40
		loop 2
	)
)

(class Turtle of Act
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		penColor 0
		penDown 0
	)
	
	(method (doit)
		(super doit:)
		(if penDown
			(DrawCel 40 1 (if (!= penColor 7) 2 else 0) x y 1)
		)
	)
)

(instance courseMarker of Turtle
	(properties
		view 40
		loop 1
		penColor 7
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: ignoreActors: 1 ignoreControl: -32768)
	)
	
	(method (doit)
		(if
		(and (& (proc802_0 self 3) $2000) (!= penColor 0))
			(= local141 1)
		)
		(super doit: &rest)
	)
)

(instance drawLastLine of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= local139 (NodeValue (markerList last:)))
				(= temp0
					(NodeValue (markerList prev: (markerList last:)))
				)
				(courseMarker posn: (temp0 x?) (temp0 y?))
				(courseMarker
					penColor: 7
					setMotion: MoveTo (local139 x?) (local139 y?) self
				)
			)
			(1
				(localproc_0bf5)
				(self dispose:)
			)
		)
	)
)

(instance backOneScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (<= (markerList size?) 1)
					(self dispose:)
				else
					(self setScript: unDrawLast self)
				)
			)
			(1
				(self setScript: unDrawLast self)
			)
			(2
				(PlotCourse start: 1)
				(chartRm setScript: PlotCourse)
				(self dispose:)
			)
		)
	)
)

(instance unDrawLast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(IsObject
						(= local140 (markerList at: (- (markerList size?) 2)))
					)
					(= local139 (NodeValue (markerList last:)))
					(courseMarker
						posn: (local140 x?) (local140 y?)
						penColor: 0
						setMotion: MoveTo (local139 x?) (local139 y?) self
					)
				else
					(self dispose:)
				)
			)
			(1
				(ego posn: (local140 x?) (local140 y?))
				(markerList delete: local139)
				(local139 dispose:)
				(localproc_0bf5)
				(self dispose:)
			)
		)
	)
)

(instance startPlot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 6)
				(= start 1)
				(self cue:)
			)
			(1
				(if register
					(-- register)
					(= cycles 2)
				else
					(= start 0)
					(PlotCourse start: 1)
					(client setScript: PlotCourse)
				)
			)
			(2
				(self setScript: unDrawLast self)
			)
			(3 (self init:))
		)
	)
)

(instance PlotCourse of Script
	(properties)
	
	(method (doit &tmp temp0 temp1)
		(if
			(and
				local54
				(or (!= (ego x?) egoX_2) (!= (ego y?) egoY_2))
			)
			(= egoX_2 (ego x?))
			(= egoY_2 (ego y?))
			(if local24
				(Display @local24 dsCOORD 100 172 dsCOLOR 0 dsFONT 104)
			)
			(Display
				(Format
					@local24
					40
					13
					(Abs (= temp1 (localproc_0a8b (ego y?))))
					(if (< 0 temp1) {N} else {S})
					(Abs (= temp0 (localproc_09b8 (ego x?))))
					(if (< 0 temp0) {W} else {E})
				)
				dsCOORD
				100
				172
				dsCOLOR
				10
				dsFONT
				104
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= local54 1)
				(User canControl: 1)
			)
			(2
				(= local54 0)
				(User canControl: 0)
				(cond 
					(
					(and (== egoX_3 (ego x?)) (== egoY_3 (ego y?))) (self changeState: 6))
					(
						(or
							(& (OnControl 4 (ego x?) (ego y?)) $2000)
							(& (localproc_0f40 (ego x?) (ego y?) 1) $2000)
						)
						(Print 40 14)
						(self start: 1 init:)
					)
					(else
						(= egoX_3 (ego x?))
						(= egoY_3 (ego y?))
						(markerList
							addToEnd:
								((locationMarker new:)
									x: egoX_3
									y: egoY_3
									init:
									setCycle: Fwd
									yourself:
								)
						)
						(= cycles 2)
					)
				)
			)
			(3
				(self setScript: drawLastLine self)
			)
			(4
				(if (== local141 1)
					(= local141 0)
					(Print 40 15)
					(self setScript: unDrawLast self)
				else
					(= cycles 2)
				)
			)
			(5
				(if (>= (markerList size?) 6)
					(Print 40 16)
					(= cycles 2)
				else
					(self changeState: 1)
				)
			)
			(6
				(markerList
					addToEnd:
						((locationMarker new:)
							x: local65
							y: local66
							init:
							setCycle: Fwd
							yourself:
						)
				)
				(self setScript: drawLastLine self)
			)
			(7
				(cond 
					((== local141 1) (Print 40 15) (self setScript: unDrawLast self))
					((localproc_1458)
						(Print 40 17)
						(theGame
							changeScore:
								(cond 
									((< local169 2) 10)
									((< local169 3) 5)
									((< local169 5) 4)
									((< local169 8) 3)
									((< local169 11) 2)
									(else 1)
								)
						)
						(= cycles 2)
					)
					(else
						(if local170
							(= local170 0)
							(Print 40 18)
						else
							(Print 40 19)
							(Print 40 20)
						)
						(Print 40 21)
						(self dispose:)
					)
				)
			)
			(8
				(if (== local141 1)
					(= local141 0)
					(= cycles 2)
				else
					(= local142 1)
					(self dispose:)
				)
			)
			(9
				(self setScript: unDrawLast self)
			)
			(10 (self start: 1 init:))
		)
	)
	
	(method (handleEvent event)
		(if (& (event modifiers?) emSHIFT)
			(if (== (ego xStep?) 1)
				(ego xStep: 4)
				(ego yStep: 4)
			else
				(ego xStep: 1)
				(ego yStep: 1)
			)
		)
		(cond 
			((super handleEvent: event))
			(
				(and
					local54
					(Said 'alter,change/course,coordinate,coordinate')
				)
				(self start: 0 init:)
				(self setScript: unDrawLast self)
			)
			(
				(and
					local54
					(== (event type?) evKEYBOARD)
					(== (event message?) KEY_RETURN)
				)
				(event claimed: 1)
				(User canControl: 0)
				(self cue:)
			)
		)
	)
)

(instance stealMouse of Script
	(properties)
	
	(method (handleEvent event)
		(if (== (event type?) evMOUSEBUTTON)
			(event claimed: 1)
		)
	)
)
