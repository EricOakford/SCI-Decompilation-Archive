;;; Sierra Script 1.0 - (do not remove this comment)
(script# 648)
(include sci.sh)
(use Main)
(use TextIcon)
(use String)
(use Array)
(use Print)
(use ForCount)
(use Actor)
(use System)

(public
	ticTacPuz 0
)

(local
	local0
	local1
	theGGTheObj_2X
	local3
	local4
	local5
	local6
	local7
)
(procedure (localproc_0ac1 param1)
	(return (+ 40 (* (mod param1 3) (CelWide 645 0 0))))
)

(procedure (localproc_0ad7 param1)
	(return (+ 35 (* (/ param1 3) (CelHigh 645 0 0))))
)

(procedure (localproc_0aed param1 &tmp temp0 theTheGGTheObj_2X temp2)
	(= gGTheObj_2X theGGTheObj_2X)
	(= theTheGGTheObj_2X theGGTheObj_2X)
	(if (== param1 rotating)
		(if (!= local0 -1)
			(= theTheGGTheObj_2X
				(| theTheGGTheObj_2X (<< $0001 local1))
			)
		)
	else
		(= theTheGGTheObj_2X
			(| theTheGGTheObj_2X (<< $0001 local0))
		)
	)
	(localproc_0e11)
	(if (> local7 7)
		(= temp2 0)
		(while (< temp2 9)
			(if (not (& theGGTheObj_2X (<< $0001 temp2)))
				(return temp2)
			)
			(++ temp2)
		)
		(SetDebug)
	else
		(while
		(& theTheGGTheObj_2X (<< $0001 (= temp0 (Random 0 8))))
		)
	)
	(return temp0)
)

(procedure (localproc_0b60)
	(return
		(cond 
			(
				(and
					(== ((local3 at: 0) value2?) ((local3 at: 1) value2?))
					(== ((local3 at: 2) value2?) ((local3 at: 1) value2?))
				)
				(= local6 1)
				(return 1)
			)
			(
				(and
					(== ((local3 at: 3) value2?) ((local3 at: 4) value2?))
					(== ((local3 at: 5) value2?) ((local3 at: 4) value2?))
				)
				(= local6 2)
				(return 1)
			)
			(
				(and
					(== ((local3 at: 6) value2?) ((local3 at: 7) value2?))
					(== ((local3 at: 8) value2?) ((local3 at: 7) value2?))
				)
				(= local6 3)
				(return 1)
			)
			(
				(and
					(== ((local3 at: 0) value2?) ((local3 at: 3) value2?))
					(== ((local3 at: 6) value2?) ((local3 at: 3) value2?))
				)
				(= local6 4)
				(return 1)
			)
			(
				(and
					(== ((local3 at: 1) value2?) ((local3 at: 7) value2?))
					(== ((local3 at: 4) value2?) ((local3 at: 7) value2?))
				)
				(= local6 5)
				(return 1)
			)
			(
				(and
					(== ((local3 at: 2) value2?) ((local3 at: 8) value2?))
					(== ((local3 at: 5) value2?) ((local3 at: 8) value2?))
				)
				(= local6 6)
				(return 1)
			)
			(
				(and
					(== ((local3 at: 0) value2?) ((local3 at: 4) value2?))
					(== ((local3 at: 8) value2?) ((local3 at: 4) value2?))
				)
				(= local6 7)
				(return 1)
			)
			(
				(and
					(== ((local3 at: 6) value2?) ((local3 at: 4) value2?))
					(== ((local3 at: 2) value2?) ((local3 at: 4) value2?))
				)
				(= local6 8)
				(return 1)
			)
			(else (= local6 0) (return 0))
		)
	)
)

(procedure (localproc_0e11 &tmp temp0)
	(= local7 0)
	(= temp0 0)
	(while (< temp0 9)
		(if (& theGGTheObj_2X (<< $0001 temp0)) (++ local7))
		(++ temp0)
	)
	(return (if (>= local7 7) (return 1) else (return 0)))
)

(instance ticTacPuz of PuzzleBar
	(properties)
	
	(method (init)
		(= local3 (IntArray new: 9))
		(super init: &rest)
		(rotating init: puzzleCast hide:)
		(squaring init: puzzleCast hide:)
	)
	
	(method (dispose)
		(local3 dispose:)
		(super dispose:)
		(return (if local4 (return 1) else (return 0)))
	)
	
	(method (resetPuzzle &tmp temp0 temp1 newASquare)
		(= theGGTheObj_2X 0)
		(= local4 0)
		(= local0 -1)
		(= temp0 0)
		(while (< temp0 9)
			(= newASquare (ASquare new:))
			(newASquare
				loop: 4
				nsTop: (= temp1 (+ 35 (* (/ temp0 3) (CelHigh 645 0 0))))
				y: newASquare
				nsLeft: (= temp1 (+ 40 (* (mod temp0 3) (CelWide 645 0 0))))
				x: newASquare
				value: temp0
				value2: (+ temp0 10)
				setCel: 1
				init: self
			)
			(local3 at: temp0 newASquare)
			(self add: newASquare)
			(++ temp0)
		)
		(self setScript: thisScript)
	)
	
	(method (helpYou &tmp newStr)
		(= newStr (Str new:))
		(switch local5
			(0
				(= local5 1)
				(Message msgGET 641 1 9 0 1 (newStr data?))
				(Print addText: (newStr data?) y: 150 init:)
			)
			(1
				(= local5 2)
				(Message msgGET 641 1 9 0 2 (newStr data?))
				(Print addText: (newStr data?) y: 150 init:)
			)
			(2
				(= local5 3)
				(Message msgGET 641 1 9 0 3 (newStr data?))
				(Print addText: (newStr data?) y: 150 init:)
			)
			(3
				(= local5 4)
				(Message msgGET 641 1 9 0 4 (newStr data?))
				(Print addText: (newStr data?) y: 150 init:)
			)
			(else 
				(Message msgGET 641 1 9 0 5 (newStr data?))
				(Print addText: (newStr data?) y: 150 init:)
				(= local4 1)
				(ticTacPuz setScript: leaveScript)
			)
		)
		(newStr dispose:)
	)
	
	(method (setPlane)
		(= usePlane 1)
		(plane
			bitmap: 0
			priority: (+ (GetHighPlanePri) 1)
			init:
			setRect: 82 40 250 155
			setBitmap: 935 0 0 1
			addCast: puzzleCast
		)
	)
)

(class ASquare of TextIcon
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 55
		fixPriority 1
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view 645
		loop 0
		cel 2
		bitmap 0
		yStep 2
		signal $0001
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		type $4000
		message -1
		modifiers $0000
		mainView 0
		mainLoop 0
		mainCel 0
		maskView 0
		maskLoop 0
		maskCel 0
		cursorView -1
		cursorLoop -1
		cursorCel -1
		highlightColor 7
		lowlightColor 0
		helpVerb 0
		object 0
		selector 0
		owner 0
		value 0
		value2 0
		value3 0
		font 999
		text 0
		textColor 50
		textType 0
		downClick 0
		upClick 0
		upClickOff 0
		cycler 0
		cycleSpeed 6
	)
	
	(method (init)
		(super init: &rest)
		(= nsRight (+ nsLeft (CelWide 645 0 0)))
		(= nsBottom (+ nsTop (CelHigh 645 0 0)))
	)
	
	(method (select &tmp [temp0 3] theSquaring)
		(return
			(if (or (== value local0) (== value local1))
				(cond 
					((== value local1)
						(= theSquaring squaring)
						(= value2 (= loop (squaring cel?)))
						(= value3 (squaring cel?))
						(= theGGTheObj_2X (| theGGTheObj_2X (<< $0001 local1)))
					)
					((== value local0)
						(= theSquaring rotating)
						(= value2 (= loop (rotating cel?)))
						(= value3 (rotating cel?))
						(= theGGTheObj_2X (| theGGTheObj_2X (<< $0001 local0)))
					)
					(else (return 0))
				)
				(= loop value3)
				(UpdateScreenItem self)
				(cond 
					((localproc_0b60) (= local4 1) (ticTacPuz setScript: sHighLightBoxes))
					((== theGGTheObj_2X 511) (ticTacPuz setScript: sHighLightBoxes))
				)
			else
				0
			)
		)
	)
	
	(method (highlight)
	)
	
	(method (setCycle cType)
		(if cycler (cycler dispose:))
		(if cType
			(= cycler
				(if (& (cType -info-?) $8000) (cType new:) else cType)
			)
			(cycler init: self &rest)
		else
			(= cycler 0)
		)
	)
)

(instance rotating of Prop
	(properties
		view 645
		loop 5
		signal $4001
	)
)

(instance squaring of Prop
	(properties
		view 645
		loop 5
		signal $4001
	)
)

(instance thisScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ticTacPuz noHands: 1)
				(= cycles 2)
			)
			(1
				(rotating
					x: (localproc_0ac1 (= local0 (localproc_0aed rotating)))
					y: (localproc_0ad7 local0)
					cel: (Random 0 3)
					setLoop: 5
					setPri: 100
					show:
					setScript: theTimer1
				)
				(squaring
					x: (localproc_0ac1 (= local1 (localproc_0aed squaring)))
					y: (localproc_0ad7 local1)
					cel: (Random 0 3)
					setLoop: 5
					setPri: 100
					setScript: theTimer2
					show:
				)
				(ticTacPuz noHands: 0)
				(self dispose:)
			)
		)
	)
)

(instance theTimer1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks
					(*
						(/ [egoStats 9] 10)
						(switch arcadeLevel
							(1 5)
							(2 3)
							(3 1)
						)
					)
				)
			)
			(1
				(if (!= theGGTheObj_2X 511)
					(rotating
						x: (localproc_0ac1 (= local0 (localproc_0aed rotating)))
					)
					(rotating y: (localproc_0ad7 local0))
					(rotating cel: (Random 0 3))
					(= ticks
						(*
							(/ [egoStats 9] 10)
							(switch arcadeLevel
								(1 5)
								(2 3)
								(3 1)
							)
						)
					)
				else
					(self dispose:)
				)
			)
			(2 (self changeState: 1))
		)
	)
)

(instance theTimer2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks
					(*
						(/ [egoStats 9] 10)
						(if (== arcadeLevel 1) 5 else 3)
					)
				)
			)
			(1
				(if (and (localproc_0e11) (== theGGTheObj_2X 511))
					(self dispose:)
				else
					(squaring
						x: (localproc_0ac1 (= local1 (localproc_0aed squaring)))
					)
					(squaring y: (localproc_0ad7 local1))
					(squaring cel: (Random 0 3))
					(= ticks
						(*
							(/ [egoStats 9] 10)
							(if (== arcadeLevel 1) 5 else 3)
						)
					)
				)
			)
			(2 (self changeState: 1))
		)
	)
)

(instance toCycle of Code
	(properties)
	
	(method (doit param1 &tmp temp0)
		(if
			(and
				(param1 isKindOf: ASquare)
				(= temp0 (param1 cycler?))
			)
			((param1 cycler?) doit:)
		)
	)
)

(instance sHighLightBoxes of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(ticTacPuz eachElementDo: #perform toCycle)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ticTacPuz noHands: 1)
				(if (rotating script?)
					((rotating script?) caller: 0)
					((rotating script?) dispose:)
				)
				(if (squaring script?)
					((squaring script?) caller: 0)
					((squaring script?) dispose:)
				)
				(rotating dispose:)
				(squaring dispose:)
				(switch local6
					(0
						(ticTacPuz setScript: leaveScript)
					)
					(1
						((local3 at: 0) setCycle: ForwardCounter 6)
						((local3 at: 1) setCycle: ForwardCounter 6)
						((local3 at: 2) setCycle: ForwardCounter 6 self)
					)
					(2
						((local3 at: 3) setCycle: ForwardCounter 6)
						((local3 at: 4) setCycle: ForwardCounter 6)
						((local3 at: 5) setCycle: ForwardCounter 6 self)
					)
					(3
						((local3 at: 6) setCycle: ForwardCounter 6)
						((local3 at: 7) setCycle: ForwardCounter 6)
						((local3 at: 8) setCycle: ForwardCounter 6 self)
					)
					(4
						((local3 at: 0) setCycle: ForwardCounter 6)
						((local3 at: 3) setCycle: ForwardCounter 6)
						((local3 at: 6) setCycle: ForwardCounter 6 self)
					)
					(5
						((local3 at: 1) setCycle: ForwardCounter 6)
						((local3 at: 4) setCycle: ForwardCounter 6)
						((local3 at: 7) setCycle: ForwardCounter 6 self)
					)
					(6
						((local3 at: 2) setCycle: ForwardCounter 6)
						((local3 at: 5) setCycle: ForwardCounter 6)
						((local3 at: 8) setCycle: ForwardCounter 6 self)
					)
					(7
						((local3 at: 0) setCycle: ForwardCounter 6)
						((local3 at: 4) setCycle: ForwardCounter 6)
						((local3 at: 8) setCycle: ForwardCounter 6 self)
					)
					(8
						((local3 at: 2) setCycle: ForwardCounter 6)
						((local3 at: 4) setCycle: ForwardCounter 6)
						((local3 at: 6) setCycle: ForwardCounter 6 self)
					)
				)
			)
			(1
				(ticTacPuz setScript: leaveScript)
			)
		)
	)
)

(instance leaveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ticTacPuz state: (& (ticTacPuz state?) $ffdf))
				(if (== theGGTheObj_2X 511) (Bset 182))
				(self dispose:)
			)
		)
	)
)
