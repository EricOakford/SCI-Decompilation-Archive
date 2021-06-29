;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include sci.sh)
(use Main)
(use Intrface)
(use TWRoom)
(use Print)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm100 0
)

(local
	local0
)
(instance rm100 of ADRoom
	(properties
		modNum 110
		picture 99
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(allOfIt init:)
		((ScriptID 895 1) get: 5)
		(self setScript: startScr)
	)
	
	(method (cue)
		(switch (theMusic prevSignal?)
			(10 (sierraT show:))
			(20 (discoveryT show:))
			(30 (seriesT show:))
			(else  (startScr cue:))
		)
	)
	
	(method (newRoom n)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(allOfIt dispose:)
		(theGame handsOn:)
		(theIconBar enable:)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance startScr of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (> state 1)
			(Palette
				palANIMATE
				112
				127
				-7
				128
				143
				-5
				144
				159
				-7
				192
				107
				-5
				96
				111
				-7
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (FileIO fiEXISTS {g})
					(Bclr 77)
					(= local0 -1)
					(while (== local0 -1)
						(= local0 (GetNumber {Teleport to}))
					)
					(curRoom newRoom: local0)
				else
					(LoadMany 128 51 996)
					(Load rsVIEW 51)
					(theIconBar disable:)
					(theMusic number: 1 setLoop: 1 play: curRoom)
					(smallGear init: hide:)
					(mediumGear init: hide:)
					(bigGear init: hide:)
					(sierraT init: hide:)
					(discoveryT init: hide:)
					(seriesT init: hide:)
					(logoLeft init:)
					(logoRight init:)
					(= ticks 5)
				)
			)
			(1
				(theGame setCursor: 996 1)
				(if (Btst 165)
					(logoLeft xStep: 8 moveSpeed: 0)
					(logoRight xStep: 8 moveSpeed: 0)
					(smallGear cycleSpeed: 0 moveSpeed: 0)
					(mediumGear cycleSpeed: 0 moveSpeed: 0)
					(bigGear cycleSpeed: 0 moveSpeed: 0)
				else
					(logoLeft xStep: 3 moveSpeed: 0)
					(logoRight xStep: 3 moveSpeed: 0)
					(smallGear cycleSpeed: 2 moveSpeed: 1)
					(mediumGear cycleSpeed: 2 moveSpeed: 1)
					(bigGear cycleSpeed: 2 moveSpeed: 1)
				)
				(= ticks 19)
			)
			(2
				(logoLeft setMotion: MoveTo 140 169 self)
				(logoRight setMotion: MoveTo 140 169 self)
			)
			(3 0)
			(4
				(logoLeft setCel: 2 stopUpd:)
				(logoRight dispose:)
				(smallGear
					show:
					setCycle: Forward
					setPri: 14
					setMotion: MoveTo 118 65
				)
				(mediumGear
					show:
					setCycle: Forward
					setPri: 14
					setMotion: MoveTo 199 58
				)
				(= ticks 20)
			)
			(5
				(bigGear
					show:
					setCycle: Forward
					setPri: 14
					setMotion: MoveTo 186 141
				)
			)
			(6
				(theGame setCursor: 999 1)
				(= cycles 1)
			)
			(7
				(curRoom drawPic: 50 10)
				(allOfIt eachElementDo: #dispose)
				(if (not debugging)
					(= local0 110)
					(= gameAct 1)
					(++ state)
				)
				(= seconds 3)
			)
			(8
				(= local0 0)
				(repeat
					(switch
						(Print
							addText: {Where to?}
							addButton: 0 {Teleport} 0 12
							addButton: 1 {StartGame} 0 26
							addButton: 2 {Restore} 0 40
							init:
						)
						(0
							(Bclr 77)
							(= local0 (GetNumber {Teleport to}))
						)
						(1
							(= local0 110)
							(= gameAct 1)
						)
						(2 (theGame restore:))
					)
					(if local0 (break))
				)
				(= cycles 2)
			)
			(9
				(allOfIt dispose:)
				(curRoom newRoom: local0)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(OneOf (event type?) 1 4 256)
				(not (event modifiers?))
			)
			(event claimed: 1)
			(if (< state 5)
				(theMusic fade:)
				(allOfIt eachElementDo: #dispose)
				(self changeState: 6)
			)
		)
		(event claimed: 1)
	)
)

(instance logoLeft of Actor
	(properties
		x -100
		y 169
		z 100
		view 51
		priority 15
		signal $4810
		origStep 771
	)
)

(instance logoRight of Actor
	(properties
		x 380
		y 169
		z 100
		view 51
		cel 1
		priority 15
		signal $4810
		origStep 771
	)
)

(instance smallGear of Actor
	(properties
		x 138
		y 85
		view 51
		loop 1
		cel 3
		priority 15
		signal $4810
		origStep 257
	)
)

(instance mediumGear of Actor
	(properties
		x 167
		y 90
		view 51
		loop 2
		cel 3
		priority 15
		signal $4810
		origStep 257
	)
)

(instance bigGear of Actor
	(properties
		x 135
		y 90
		view 51
		loop 3
		cel 2
		priority 15
		signal $4810
		origStep 257
	)
)

(instance sierraT of View
	(properties
		x 211
		y 98
		view 51
		loop 4
		priority 15
		signal $4010
	)
)

(instance discoveryT of View
	(properties
		x 211
		y 98
		view 51
		loop 4
		cel 1
		priority 15
		signal $4010
	)
)

(instance seriesT of View
	(properties
		x 211
		y 98
		view 51
		loop 4
		cel 2
		priority 15
		signal $4010
	)
)

(instance allOfIt of List
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			add:
				logoRight
				logoLeft
				smallGear
				mediumGear
				bigGear
				sierraT
				discoveryT
				seriesT
		)
	)
)
