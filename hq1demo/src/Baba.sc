;;; Sierra Script 1.0 - (do not remove this comment)
(script# BABA)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Extra)
(use Save)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	baba 0
)

(local
	hutX
	hutY
)
(instance baba of Room
	(properties
		picture rBabaHutOutside
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW rBabaHutOutside vBabaSkulls)
		(Load SOUND sBabaHutOutside)
		(= currentPalette 0)
		(super init:)
		(music number: sBabaHutOutside play:)
		(ego
			view: vEgoStanding
			loop: loopN
			cel: 0
			posn: 181 189
			init:
		)
		(= hutX 167)
		(= hutY 96)
		(hut
			setLoop: 4
			ignoreActors:
			posn: hutX hutY
			setPri: 4
			init:
			stopUpd:
		)
		(frontFoot
			setLoop: 1
			setPri: 3
			ignoreActors:
			posn: (- hutX 27) (- hutY 7)
			init:
			stopUpd:
		)
		(backFoot
			setLoop: 3
			setPri: 2
			ignoreActors:
			posn: (+ hutX 38) (- hutY 8)
			init:
			stopUpd:
		)
		(gate setLoop: 0 setPri: 11 init: stopUpd:)
		(dirt ignoreActors: setPri: 11 init: stopUpd:)
		(bonehead
			ignoreActors:
			setCycle: Forward
			setPri: 11
			init:
			stopUpd:
		)
		(eyes ignoreActors: setPri: 12 init: setCycle: Forward)
		(skull1 ignoreActors: setPri: 12 init:)
		(skull2 ignoreActors: setPri: 12 init:)
		(skull3 ignoreActors: setPri: 12 init:)
		(skull4 ignoreActors: setPri: 12 init:)
		(skull5 ignoreActors: setPri: 12 init:)
		(self setScript: startIt)
	)
	
	(method (dispose)
		(DisposeScript 988)
		(super dispose:)
	)
)

(instance startIt of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
			)
			(1
				(Print 7 0
					#at -1 290
					#width 300
					#dispose
					#window aWin
				)
				(self setScript: flyAway)
			)
		)
	)
)

(instance flyAway of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(skull1 stopUpd: addToPic:)
				(skull2 stopUpd: addToPic:)
				(skull3 stopUpd: addToPic:)
				(skull4 stopUpd: addToPic:)
				(skull5 stopUpd: addToPic:)
				(if (cast contains: gate)
					(gate addToPic:)
				)
				(if (cast contains: dirt)
					(dirt addToPic:)
				)
				(if (cast contains: bonehead)
					(bonehead addToPic:)
				)
				(if (cast contains: eyes)
					(eyes addToPic:)
				)
				(= seconds 5)
			)
			(1
				(wing1
					ignoreActors:
					ignoreHorizon:
					setPri: 10
					posn: (- hutX 36) (- hutY 57)
					init:
					setCycle: EndLoop self
				)
				(wing2
					ignoreActors:
					ignoreHorizon:
					posn: (+ hutX 46) (- hutY 52)
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(hutDoor dispose:)
				(wing1 setLoop: 6 setCycle: Forward)
				(wing2 setLoop: 7 setCycle: Forward)
				(= cycles 12)
			)
			(3
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(hut setMotion: MoveTo hutX -20)
				(wing1 setMotion: MoveTo (wing1 x?) -15)
				(wing2 setMotion: MoveTo (wing2 x?) -15)
				(frontFoot
					ignoreHorizon:
					setMotion: MoveTo (frontFoot x?) -25
				)
				(backFoot
					ignoreHorizon:
					setMotion: MoveTo (backFoot x?) -25
				)
				(= seconds 10)
			)
			(4
				(curRoom newRoom: CAVE)
			)
		)
	)
)

(instance bonehead of Actor
	(properties
		y 137
		x 190
		z 1
		view vBabaSkulls
		loop 2
		illegalBits 0
	)
)

(instance wing1 of Actor
	(properties
		yStep 4
		view rBabaHutOutside
		loop 8
		illegalBits 0
		xStep 6
	)
)

(instance wing2 of Actor
	(properties
		yStep 4
		view rBabaHutOutside
		loop 9
		illegalBits 0
		xStep 6
	)
)

(instance hut of Actor
	(properties
		yStep 4
		view rBabaHutOutside
		loop 4
		illegalBits 0
		xStep 6
	)
)

(instance hutDoor of Actor
	(properties
		view rBabaHutOutside
		loop 5
		cycleSpeed 1
		illegalBits 0
	)
)

(instance frontLeg of Actor
	(properties
		yStep 4
		view rBabaHutOutside
		illegalBits 0
		xStep 6
	)
)

(instance frontFoot of Actor
	(properties
		yStep 4
		view rBabaHutOutside
		loop 1
		cel 1
		illegalBits 0
		xStep 6
	)
)

(instance backLeg of Actor
	(properties
		yStep 4
		view rBabaHutOutside
		loop 2
		illegalBits 0
		xStep 6
	)
)

(instance backFoot of Actor
	(properties
		yStep 4
		view rBabaHutOutside
		loop 3
		cel 1
		illegalBits 0
		xStep 6
	)
)

(instance gate of Actor
	(properties
		y 158
		x 191
		view vBabaSkulls
		illegalBits 0
	)
)

(instance dirt of Actor
	(properties
		y 156
		x 191
		view vBabaSkulls
		loop 1
		illegalBits 0
	)
)

(instance eyes of Actor
	(properties
		y 130
		x 189
		z 2
		view vBabaSkulls
		loop 3
		cycleSpeed 1
		illegalBits 0
	)
)

(instance skull1 of Extra
	(properties
		y 88
		x 117
		view vBabaSkulls
		loop 4
		cycleType ExtraEndLoop
		minPause 40
		maxPause 80
		minCycles 2
		maxCycles 4
	)
)

(instance skull2 of Extra
	(properties
		y 89
		x 251
		view vBabaSkulls
		loop 4
		cycleType ExtraEndLoop
		minPause 40
		maxPause 80
		minCycles 2
		maxCycles 4
	)
)

(instance skull3 of Extra
	(properties
		y 88
		x 72
		view vBabaSkulls
		loop 6
		cycleType ExtraEndLoop
		minPause 40
		maxPause 80
		minCycles 2
		maxCycles 4
	)
)

(instance skull4 of Extra
	(properties
		y 85
		x 14
		view vBabaSkulls
		loop 5
		cycleType ExtraEndLoop
		minPause 40
		maxPause 80
		minCycles 2
		maxCycles 4
	)
)

(instance skull5 of Extra
	(properties
		y 82
		x 295
		view vBabaSkulls
		loop 5
		cycleType ExtraEndLoop
		minPause 40
		maxPause 80
		minCycles 2
		maxCycles 4
	)
)

(instance aWin of SysWindow
	(properties
		color vLCYAN
		back vBLACK
	)
)
