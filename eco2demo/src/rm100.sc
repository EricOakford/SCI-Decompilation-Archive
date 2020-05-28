;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh)
(use Main)
(use MoveCyc)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm100 0
)

(local
	[planeCycle1 47] = [0 0 128 62 1 0 129 62 2 0 160 103 -3599 104 3 0 205 105 4 0 238 117 -3856 91 -3599 128 1 0 270 117 2 0 295 115 -3599 106 3 0 310 107 -3599 160 13 0 325 99 -32768]
	[planeCycle2 65] = [0 0 128 62 1 0 129 62 2 0 160 103 -3599 104 3 0 205 105 4 0 238 117 -3856 91 -3599 128 1 0 270 117 2 0 295 115 -3599 106 3 0 310 107 -3599 89 4 0 312 102 5 0 273 95 6 0 265 85 7 0 265 79 -3599 81 8 0 250 73 -32768]
	local112
	[str 201]
)
(instance rm100 of Room
	(properties
		picture 41
	)
	
	(method (init &tmp temp0)
		(LoadMany RES_SOUND 40 45 30)
		(LoadMany RES_VIEW 90 91 92)
		(if (= temp0 (== prevRoomNum 150))
			(= style PIXELDISSOLVE)
		)
		(super init: &rest)
		(self setScript: flyingInScr)
	)
)

(class MyMoveCycle of MoveCycle
	
	(method (nextCel &tmp temp0 temp1)
		(cond 
			((== (= temp0 (WordAt points value)) -3856)
				(client view: (WordAt points (+ value 1)))
				(= value (+ value (* cycleDir 2)))
				(self nextCel:)
				(return)
			)
			((== temp0 -3599)
				(= temp1 (WordAt points (+ value 1)))
				(= value (+ value (* cycleDir 2)))
				(client scaleX: temp1 scaleY: temp1)
				(self nextCel:)
				(return)
			)
		)
		(if (> local112 1)
			(client cycleSpeed: (+ (client cycleSpeed?) 1))
		else
			(++ local112)
		)
		(client
			loop: temp0
			cel: (WordAt points (+ value 1))
			x: (WordAt points (+ value 2))
			y: (WordAt points (+ value 3))
		)
		(= value (+ value (* cycleDir 4)))
		(if
			(or
				(and (== cycleDir 1) (>= value size))
				(and (== cycleDir -1) (< value 0))
			)
			(self cycleDone:)
		)
	)
)

(instance flyingInScr of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(Palette PALLoad 100)
				(theMusic number: 45 loop: -1 play:)
				(= ticks 120)
			)
			(1
				(theText init:)
				(= ticks 240)
			)
			(2
				(theText dispose:)
				(= ticks 60)
			)
			(3
				(soundFx
					number: 40
					loop: -1
					play: 1 self
					fade: 127 15 9 0
				)
			)
			(4 (= ticks 60))
			(5
				(thePlane init:)
				(= cycles 2)
			)
			(6
				(soundFx client: 0 fade: 50 10 12 0)
				(thePlane
					cycleSpeed: 2
					setCycle: MyMoveCycle @planeCycle1 self
				)
			)
			(7 (= cycles 2))
			(8
				(thePlane cycleSpeed: 6 setScript: planeToRightScr self)
			)
			(9
				(theMusic fade:)
				(PalVary PALVARYSTART 101 3 64 1)
				(= ticks 180)
			)
			(10
				(PalVary PALVARYKILL)
				(= ticks 10)
			)
			(11
				(PalVary PALVARYSTART 41 4 64 1)
				(= ticks 260)
			)
			(12
				(PalVary PALVARYKILL)
				(soundFx fade: 127 10 12 0 client: self)
			)
			(13
				(soundFx client: 0 fade: 0 10 10 1)
				(thePlane
					setLoop: -1
					scaleX: 128
					scaleY: 128
					view: 90
					loop: 0
					cel: 0
					posn: 128 62
					cycleSpeed: 2
					setCycle: MyMoveCycle @planeCycle2 self
				)
			)
			(14 (= ticks 10))
			(15
				(thePlane hide:)
				(ecoTitle init:)
				(= ticks 480)
				(theMusic number: 30 loop: -1 play:)
			)
			(16 (curRoom newRoom: 110))
		)
	)
)

(instance planeToRightScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(thePlane
					signal: (| (thePlane signal?) fixedLoop)
					setCycle: EndLoop thePlane
					setStep: 1 1
					moveSpeed: 7
					setMotion: MoveTo (+ (thePlane x?) 20) (thePlane y?)
				)
				(self cue:)
			)
			(1
				(thePlane
					scaleX: (- (thePlane scaleX?) 4)
					scaleY: (- (thePlane scaleY?) 4)
				)
				(if (> (thePlane scaleX?) 20) (-- state))
				(= cycles 2)
			)
			(2 (self dispose:))
		)
	)
)

(instance ecoTitle of View
	(properties
		x 46
		y 50
		view 92
		signal stopUpdOn
	)
)

(instance thePlane of Actor
	(properties
		x 128
		y 62
		view 90
		cycleSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self setScale:)
	)
	
	(method (cue)
		(if (and script (IsObject script))
			(script dispose:)
		)
	)
)

(instance theText of View
	(properties
		x 44
		y 89
		view 93
		priority 15
		signal fixPriOn
	)
)
