;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm021 0
)

(local
	local0
)
(instance rm021 of Room
	(properties)
	
	(method (init &tmp [temp0 50])
		(= showStyle HSHUTTER)
		(HandsOff)
		(= global159 1)
		(TheMenuBar hide:)
		(StatusLine disable:)
		(Load VIEW 52)
		(Load SOUND 38)
		(switch shipLocation
			(shipPHLEEBHUT_LAND
				(Load PICTURE 112)
				(self drawPic: 112)
				(self setScript: PhleebScript)
			)
			(shipORTEGA_LAND
				(Load PICTURE 113)
				(self drawPic: 113)
				(self setScript: OrtegaScript)
			)
			(shipPESTULON_LAND
				(Load PICTURE 114)
				(self drawPic: 114)
				(self setScript: MoonScript)
			)
		)
		(super init:)
	)
)

(instance PhleebScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== local0 1) (== (warpOut prevSignal?) -1))
			(= local0 0)
			(curRoom newRoom: 14)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(ship
					posn: 260 204
					setLoop: 1
					init:
					setStep: 3 6
					setMotion: MoveTo 260 152 self
				)
			)
			(2
				(warpOut play:)
				(ship
					setStep: 3 3
					setCycle: EndLoop
					setMotion: MoveTo 235 136 self
				)
			)
			(3
				(ship setMotion: MoveTo 199 100 self)
			)
			(4 (= local0 1))
		)
	)
)

(instance OrtegaScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== local0 1) (== (warpOut prevSignal?) -1))
			(= local0 0)
			(curRoom newRoom: 14)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(ship
					posn: 50 204
					setLoop: 0
					setPri: 6
					init:
					setStep: 3 6
					setMotion: MoveTo 50 157 self
				)
			)
			(2
				(warpOut play:)
				(ship
					setStep: 3 3
					setCycle: EndLoop
					setMotion: MoveTo 89 118 self
				)
			)
			(3
				(ship setPri: 4 setMotion: MoveTo 145 102 self)
			)
			(4 (= local0 1))
		)
	)
)

(instance MoonScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== local0 1) (== (warpOut prevSignal?) -1))
			(= local0 0)
			(curRoom newRoom: 14)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(ship
					posn: 124 204
					setLoop: 0
					setPri: 6
					init:
					setStep: 3 6
					setMotion: MoveTo 124 157 self
				)
			)
			(2
				(warpOut play:)
				(ship
					setStep: 5 5
					setCycle: EndLoop
					setMotion: MoveTo 230 75 self
				)
			)
			(3
				(ship setPri: 4 setMotion: MoveTo 241 71 self)
			)
			(4
				(ship setStep: 3 3 setMotion: MoveTo 252 74 self)
			)
			(5
				(ship setMotion: MoveTo 261 78 self)
			)
			(6
				(ship setMotion: MoveTo 285 102 self)
			)
			(7
				(ship setPri: 6 setMotion: MoveTo 284 107 self)
			)
			(8
				(ship setMotion: MoveTo 278 106 self)
			)
			(9
				(ship setMotion: MoveTo 261 97 self)
			)
			(10
				(ship setMotion: MoveTo 249 81 self)
			)
			(11
				(ship setPri: 4 setMotion: MoveTo 253 78 self)
			)
			(12
				(ship setMotion: MoveTo 260 82 self)
			)
			(13 (= local0 1))
		)
	)
)

(instance ship of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 52
			setCel: 0
			ignoreActors: TRUE
			illegalBits: 0
			setCycle: 0
		)
	)
)

(instance warpOut of Sound
	(properties
		number 38
		priority 2
	)
)
