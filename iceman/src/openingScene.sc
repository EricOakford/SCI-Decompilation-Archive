;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	openingScene 0
)

(local
	local0
)
(procedure (DoPrint numSeconds &tmp [len 4] [str 100])
	(cls)
	(Format @str &rest)
	(TextSize @[len 0] @str userFont 0)
	(Print @str
		#at -1 12
		#width (if (> [len 2] 24) 300 else 0)
		#mode teJustCenter
		#dispose
		#time numSeconds
	)
)

(instance openingScene of Room
	(properties
		picture 1
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(self
			setScript: (if (== prevRoomNum 2) dinghyScript else openingScript)
			setRegions: rgTahiti rgWater
		)
		(miniActor1 init:)
		(miniActor2 init:)
		(miniActor3 init:)
	)
	
	(method (dispose)
		(HandsOn)
		(super dispose:)
	)
)

(instance openingScript of Script

	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(cls)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(addToPics add: littleEgo doit:)
				(= seconds 10)
			)
			(1
				(DoPrint 8 1 0)
				(= seconds 8)
			)
			(2
				(DoPrint 8 1 1)
				(= seconds 8)
			)
			(3
				(DoPrint 8 1 2)
				(= seconds 8)
			)
			(4
				(DoPrint 8 1 3)
				(= seconds 8)
			)
			(5
				(curRoom newRoom: 2)
			)
		)
	)
	
	(method (cue)
		(= cycles (= seconds 0))
		(super cue:)
	)
	
	(method (handleEvent event)
		(if (event claimed?)
			(return)
		else
			(self cue:)
		)
		(event claimed: TRUE)
	)
)

(instance littleDinghy of Actor
	(properties
		yStep 1
		view 1
		xStep 1
	)
	
	(method (init)
		(super init:)
		(boatSound number: (SoundFX 26) loop: -1 play:)
		(self setPri: 3)
	)
)

(instance dinghyScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego put: 5)
				(littleDinghy
					posn: 205 143
					init:
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 100 152 self
				)
			)
			(1
				(littleDinghy setMotion: MoveTo -10 143 self)
			)
			(2
				(= seconds 5)
			)
			(3
				(littleDinghy
					posn: -10 92
					setLoop: 1
					setMotion: MoveTo 65 80 self
				)
			)
			(4
				(littleDinghy setMotion: MoveTo 120 72 self)
			)
			(5
				(HandsOn)
				(curRoom newRoom: 44)
			)
		)
	)
)

(instance littleEgo of PicView
	(properties
		y 134
		x 211
		view 1
		loop 2
	)
)

(instance miniActor1 of Actor
	(properties
		y 134
		x 171
		yStep 1
		view 1
		xStep 1
		moveSpeed 3
	)
	
	(method (init)
		(super init:)
		(self setLoop: 3 setMotion: MoveTo 229 138 self)
	)
	
	(method (cue)
		(super cue:)
		(if (== x 171)
			(self setMotion: MoveTo 229 138 self)
		else
			(self setMotion: MoveTo 171 134 self)
		)
	)
)

(instance miniActor2 of Actor
	(properties
		y 135
		x 233
		yStep 1
		view 1
		loop 3
		xStep 1
		moveSpeed 3
	)
	
	(method (init)
		(super init:)
		(self setLoop: 3 setMotion: MoveTo 312 126 self)
	)
	
	(method (cue)
		(super cue:)
		(if (== x 233)
			(self setMotion: MoveTo 312 126 self)
		else
			(self setMotion: MoveTo 233 135 self)
		)
	)
)

(instance miniActor3 of Actor
	(properties
		y 129
		x 204
		yStep 1
		view 1
		loop 3
		xStep 1
		moveSpeed 3
	)
	
	(method (init)
		(super init:)
		(self setLoop: 3 setMotion: MoveTo 203 141 self)
	)
	
	(method (cue)
		(super cue:)
		(if (== x 204)
			(self setMotion: MoveTo 203 141 self)
		else
			(self setMotion: MoveTo 204 129 self)
		)
	)
)

(instance boatSound of Sound
	(properties
		number 26
	)
)
