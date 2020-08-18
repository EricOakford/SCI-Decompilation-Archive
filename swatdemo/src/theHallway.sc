;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh)
(use Main)
(use PQRoom)
(use Motion)
(use Actor)
(use System)

(public
	theHallway 0
)

(instance theHallway of PQRoom
	(properties
		picture 13
	)
	
	(method (init)
		(theMusic number: 300 loop: -1 play:)
		(super init: &rest)
		(Load RES_VIEW 130)
		(self setScript: demoScript)
	)
)

(instance demoScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(Bset fScrollingEnabled)
				(Bset 12)
				(= gScript self)
				(theInterface newInvLevel: 3 0)
			)
			(2
				(= register (ScriptID 908 1))
				(= temp1
					(/
						(CelWide
							(register view?)
							(register loop?)
							(register cel?)
						)
						2
					)
				)
				(= temp2
					(/
						(CelHigh
							(register view?)
							(register loop?)
							(register cel?)
						)
						2
					)
				)
				(curView
					posn: (+ (register x?) temp1) (+ (register y?) temp2)
					init: theInventoryCast
				)
				(= cycles 1)
			)
			(3 (= seconds 2))
			(4
				(register hide:)
				(soundFx number: 2045 loop: 1 play:)
				(= ticks 60)
			)
			(5
				(register show:)
				(= cycles 3)
			)
			(6
				(swatPups init: setCycle: CycleTo 3 1 self)
			)
			(7 (= cycles 14))
			(8
				(swatPups setCycle: CycleTo 54 1 self)
			)
			(9
				(theInventoryPlane
					right: (+ (theInventoryPlane right:) 20)
					inRight: (+ (theInventoryPlane inRight?) 20)
				)
				(UpdatePlane theInventoryPlane)
				(theDoits add: curView)
				(curView setMotion: MoveTo 221 9 self)
			)
			(10
				(= register (ScriptID 908 3))
				(register
					view: (register downView?)
					loop: (register downLoop?)
					cel: (register downCel?)
				)
				(soundFx number: 2045 loop: 1 play:)
				(UpdateScreenItem register)
				(= ticks 60)
			)
			(11
				(register
					view: (register upView?)
					loop: (register upLoop?)
					cel: (register upCel?)
				)
				(UpdateScreenItem register)
				(= cycles 10)
			)
			(12
				(= gScript self)
				(theInterface newInvLevel: -1 0)
			)
			(13
				(= register (ScriptID 908 2))
				(= temp1
					(/
						(CelWide
							(register view?)
							(register loop?)
							(register cel?)
						)
						2
					)
				)
				(= temp2
					(/
						(CelHigh
							(register view?)
							(register loop?)
							(register cel?)
						)
						2
					)
				)
				(curView
					posn: 221 9
					setMotion: MoveTo (+ (register x?) temp1) (+ (register y?) temp2) self
				)
				(UpdateScreenItem curView)
			)
			(14
				(theInventoryPlane
					right: (- (theInventoryPlane right:) 20)
					inRight: (- (theInventoryPlane inRight?) 20)
				)
				(UpdatePlane theInventoryPlane)
				(theDoits delete: curView)
				(register hide:)
				(soundFx number: 2045 loop: 1 play:)
				(= ticks 60)
			)
			(15
				(register show:)
				(theInterface setHandSignal: 3 self)
			)
			(16
				(swatPups setCycle: EndLoop self)
			)
			(17
				(theInterface setHandSignal: 0)
				(swatPups
					setLoop: 1
					cel: 0
					posn: 191 109
					setCycle: EndLoop self
				)
			)
			(18
				(swatPups
					setLoop: 2
					cel: 0
					posn: 143 150
					setCycle: EndLoop self
				)
			)
			(19
				(curView dispose:)
				(curRoom newRoom: 14)
			)
		)
	)
)

(instance swatPups of Prop
	(properties
		x 181
		y 100
		view 130
		cycleSpeed 10
	)
)

(instance curView of Actor
	(properties
		priority 200
		fixPriority 1
		view 999
		yStep 3
		xStep 5
		moveSpeed 3
	)
)
