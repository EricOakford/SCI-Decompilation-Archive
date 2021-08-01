;;; Sierra Script 1.0 - (do not remove this comment)
(script# 215)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use RFeature)
(use Motion)
(use System)

(public
	rm215 0
)

(instance rm215 of KQ5Room
	(properties
		picture 215
		east 8
	)
	
	(method (init)
		(super init:)
		(Load rsVIEW 792)
		(HandsOff)
		(if (== prevRoomNum 8)
			(ego
				normal: 0
				view: 792
				setLoop: 2
				setStep: 3 2
				setCycle: KQ5SyncWalk
				posn: 340 -35
				init:
			)
			(self setScript: fromRoom8)
		else
			(ego
				normal: 0
				view: 792
				setLoop: 1
				setStep: 3 2
				setCycle: KQ5SyncWalk
				illegalBits: 0
				ignoreHorizon: 1
				posn: -30 139
				init:
			)
			(self setScript: enterScreen)
		)
		((ego head?) hide:)
		(self setFeatures: room)
		(theMusic number: 87 loop: -1 playBed:)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(theMusic fade:)
	)
)

(instance enterScreen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: 2 setMotion: MoveTo 130 139 self)
			)
			(1
				(ego setMotion: MoveTo 188 124 self)
			)
			(2
				(ego setMotion: MoveTo 216 110 self)
			)
			(3
				(ego setMotion: MoveTo 231 102 self)
			)
			(4
				(ego
					setLoop: 3
					posn: 236 102
					setMotion: MoveTo 263 72 self
				)
			)
			(5
				(ego setMotion: MoveTo 282 33 self)
			)
			(6
				(ego setMotion: MoveTo 315 -25 self)
			)
			(7 (curRoom newRoom: 8))
		)
	)
)

(instance fromRoom8 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: 0 setMotion: MoveTo 264 73 self)
			)
			(1
				(ego setMotion: MoveTo 222 111 self)
			)
			(2
				(ego setMotion: MoveTo 212 113 self)
			)
			(3
				(ego
					posn: 218 125
					setLoop: 1
					setMotion: MoveTo 165 136 self
				)
			)
			(4
				(ego setMotion: MoveTo -30 141 self)
			)
			(5 (curRoom newRoom: 83))
		)
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 710)
					(event claimed: 1)
				)
			)
		)
	)
)
