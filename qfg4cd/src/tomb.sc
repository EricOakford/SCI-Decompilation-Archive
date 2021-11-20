;;; Sierra Script 1.0 - (do not remove this comment)
(script# 85)
(include sci.sh)
(use Main)
(use TextIcon)
(use Polygon)
(use Motion)
(use Actor)
(use System)

(public
	tomb 0
)

(local
	local0
	theIconBarGetCursorLoop
	theIconBarGetCursorCel
	local3
	local4
	local5
	local6
)
(instance tomb of PuzzleBar
	(properties)
	
	(method (init)
		(super init:)
		(stoneDisk init: puzzleCast)
		(stone init: puzzleCast)
	)
	
	(method (dispose)
		(theGame
			setCursor:
				((theIconBar getCursor:)
					view: local0
					loop: theIconBarGetCursorLoop
					cel: theIconBarGetCursorCel
					yourself:
				)
		)
		(super dispose:)
	)
	
	(method (resetPuzzle &tmp theIconBarGetCursor)
		(= local0
			((= theIconBarGetCursor (theIconBar getCursor:)) view?)
		)
		(= theIconBarGetCursorLoop (theIconBarGetCursor loop?))
		(= theIconBarGetCursorCel (theIconBarGetCursor cel?))
		(theGame
			setCursor: (theIconBarGetCursor view: 999 loop: 0 cel: 0 yourself:)
		)
		(= local4 0)
		(= local5 3)
		(= local6 0)
		(self
			add:
				essenceButton
				bloodButton
				heartButton
				breathButton
				senseButton
				boneButton
				mouthButton
				helpIcon
				exitIcon
			eachElementDo: #init self
			eachElementDo: #show
		)
	)
	
	(method (helpYou &tmp theIconBarGetCursor)
		(messager say: 4 9 0 (++ local3))
		(if (== local3 5)
			(Bset 361)
			(= theIconBarGetCursor (theIconBar getCursor:))
			(theGame
				setCursor:
					(theIconBarGetCursor
						view: local0
						loop: theIconBarGetCursorLoop
						cel: theIconBarGetCursorCel
						yourself:
					)
			)
			(tomb state: (& (tomb state?) $ffdf))
		)
	)
	
	(method (setPlane)
		(= usePlane 0)
		(plane
			priority: (+ (GetHighPlanePri) 1)
			init:
			setRect: 0 10 319 199
			addCast: puzzleCast
		)
	)
	
	(method (addIcons)
	)
)

(instance turnKnob of Script
	(properties)
	
	(method (changeState newState &tmp theIconBarGetCursor)
		(switch (= state newState)
			(0
				(tomb noHands: 1)
				(if (= local6 (^ local6 $0001))
					(stoneDisk
						setCycle: myCycleTo (register value2?) -1 self
					)
				else
					(stoneDisk setCycle: myCycleTo (register value2?) 1 self)
				)
			)
			(1
				(cond 
					((== local4 (register value?))
						(if (> local4 5)
							(Bset 361)
							(= theIconBarGetCursor (theIconBar getCursor:))
							(theGame
								setCursor:
									(theIconBarGetCursor
										view: local0
										loop: theIconBarGetCursorLoop
										cel: theIconBarGetCursorCel
										yourself:
									)
							)
							(tomb state: (& (tomb state?) $ffdf))
						else
							(++ local4)
							(= ticks 1)
						)
					)
					((not (-- local5))
						(= local5 3)
						(= local4 0)
						(= local6 0)
						(stoneDisk setCycle: myCycleTo 5 1 self)
					)
					(else (= ticks 1))
				)
			)
			(2
				(tomb noHands: 0)
				(self dispose:)
			)
		)
	)
)

(instance stone of View
	(properties
		x 61
		y 52
		view 556
	)
)

(instance stoneDisk of Prop
	(properties
		x 164
		y 102
		priority 245
		fixPriority 1
		view 556
		loop 1
		cel 5
		signal $4001
		cycleSpeed 12
	)
)

(instance mouthButton of TextIcon
	(properties
		nsLeft 188
		nsTop 81
		x 188
		y 81
		view 556
		loop 3
		value2 6
	)
	
	(method (init theOwner &tmp temp0)
		(= nsRight (+ nsLeft (CelWide view loop 1)))
		(= nsBottom (+ nsTop (CelHigh view loop 1)))
		(= owner theOwner)
		(= signal (& signal $fff7))
		((owner puzzleCast?) add: self)
		(= plane (owner plane?))
		(AddScreenItem self)
	)
	
	(method (select)
		(if (super select: &rest)
			(tomb setScript: turnKnob 0 self)
		)
	)
)

(instance boneButton of TextIcon
	(properties
		nsLeft 192
		nsTop 101
		x 192
		y 101
		view 556
		loop 4
		value 1
		value2 7
	)
	
	(method (init theOwner &tmp temp0)
		(= nsRight (+ nsLeft (CelWide view loop 1)))
		(= nsBottom (+ nsTop (CelHigh view loop 1)))
		(= owner theOwner)
		(= signal (& signal $fff7))
		((owner puzzleCast?) add: self)
		(= plane (owner plane?))
		(AddScreenItem self)
	)
	
	(method (select)
		(if (super select: &rest)
			(tomb setScript: turnKnob 0 self)
		)
	)
)

(instance bloodButton of TextIcon
	(properties
		nsLeft 191
		nsTop 127
		x 191
		y 127
		view 556
		loop 5
		value 2
	)
	
	(method (init theOwner &tmp temp0)
		(= nsRight (+ nsLeft (CelWide view loop 1)))
		(= nsBottom (+ nsTop (CelHigh view loop 1)))
		(= owner theOwner)
		(= signal (& signal $fff7))
		((owner puzzleCast?) add: self)
		(= plane (owner plane?))
		(AddScreenItem self)
	)
	
	(method (select)
		(if (super select: &rest)
			(tomb setScript: turnKnob 0 self)
		)
	)
)

(instance breathButton of TextIcon
	(properties
		nsLeft 160
		nsTop 136
		x 160
		y 136
		view 556
		loop 6
		value 3
		value2 1
	)
	
	(method (init theOwner &tmp temp0)
		(= nsRight (+ nsLeft (CelWide view loop 1)))
		(= nsBottom (+ nsTop (CelHigh view loop 1)))
		(= owner theOwner)
		(= signal (& signal $fff7))
		((owner puzzleCast?) add: self)
		(= plane (owner plane?))
		(AddScreenItem self)
	)
	
	(method (select)
		(if (super select: &rest)
			(tomb setScript: turnKnob 0 self)
		)
	)
)

(instance senseButton of TextIcon
	(properties
		nsLeft 130
		nsTop 123
		x 130
		y 123
		view 556
		loop 7
		value 4
		value2 2
	)
	
	(method (init theOwner &tmp temp0)
		(= nsRight (+ nsLeft (CelWide view loop 1)))
		(= nsBottom (+ nsTop (CelHigh view loop 1)))
		(= owner theOwner)
		(= signal (& signal $fff7))
		((owner puzzleCast?) add: self)
		(= plane (owner plane?))
		(AddScreenItem self)
	)
	
	(method (select)
		(if (super select: &rest)
			(tomb setScript: turnKnob 0 self)
		)
	)
)

(instance heartButton of TextIcon
	(properties
		nsLeft 124
		nsTop 106
		x 124
		y 106
		view 556
		loop 8
		value 5
		value2 3
	)
	
	(method (init theOwner &tmp temp0)
		(= nsRight (+ nsLeft (CelWide view loop 1)))
		(= nsBottom (+ nsTop (CelHigh view loop 1)))
		(= owner theOwner)
		(= signal (& signal $fff7))
		((owner puzzleCast?) add: self)
		(= plane (owner plane?))
		(AddScreenItem self)
	)
	
	(method (select)
		(if (super select: &rest)
			(tomb setScript: turnKnob 0 self)
		)
	)
)

(instance essenceButton of TextIcon
	(properties
		nsLeft 128
		nsTop 80
		x 128
		y 80
		view 556
		loop 9
		value 6
		value2 4
	)
	
	(method (init theOwner &tmp temp0)
		(= nsRight (+ nsLeft (CelWide view loop 1)))
		(= nsBottom (+ nsTop (CelHigh view loop 1)))
		(= owner theOwner)
		(= signal (& signal $fff7))
		((owner puzzleCast?) add: self)
		(= plane (owner plane?))
		(AddScreenItem self)
	)
	
	(method (select)
		(if (super select: &rest)
			(tomb setScript: turnKnob 0 self)
		)
	)
)

(instance exitIcon of TextIcon
	(properties)
	
	(method (init theOwner &tmp temp0)
		(= owner theOwner)
		((owner puzzleCast?) add: self)
		(exitPoly init:)
	)
	
	(method (dispose)
		(exitPoly dispose:)
		(DisposeClone self)
	)
	
	(method (show)
	)
	
	(method (onMe)
		(return
			(if (not (exitPoly onMe: &rest))
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (select)
		(tomb state: (& (tomb state?) $ffdf))
	)
	
	(method (highlight)
	)
)

(instance exitPoly of Polygon
	(properties)
	
	(method (init)
		(super init: 62 52 262 52 262 154 61 153)
	)
)

(instance helpIcon of TextIcon
	(properties)
	
	(method (init theOwner &tmp temp0)
		(= owner theOwner)
		((owner puzzleCast?) add: self)
	)
	
	(method (dispose)
		(DisposeClone self)
	)
	
	(method (show)
	)
	
	(method (onMe theObjOrX theY)
		(return
			(cond 
				(
					(and
						(< 64 theObjOrX)
						(< theObjOrX 116)
						(< 55 theY)
						(< theY 150)
					)
				)
				((< 214 theObjOrX) (if (< theObjOrX 258) (if (< 55 theY) (< theY 150))))
			)
		)
	)
	
	(method (select)
		(owner helpYou:)
	)
	
	(method (highlight)
	)
)

(instance myCycleTo of CT
	(properties)
	
	(method (doit &tmp clientLastCel)
		(= clientLastCel (client lastCel:))
		(cond 
			(
			(and (== cycleDir 1) (== (client cel?) clientLastCel))
				(= cycleCnt gameTime)
				(client cel: 0)
				(if (== endCel 0) (self cycleDone:))
			)
			((and (== cycleDir -1) (== (client cel?) 0))
				(= cycleCnt gameTime)
				(client cel: clientLastCel)
				(if (== endCel clientLastCel) (self cycleDone:))
			)
			(else (client cel: (self nextCel:)))
		)
		(if
		(and (== gameTime cycleCnt) (== endCel (client cel?)))
			(self cycleDone:)
		)
	)
)
