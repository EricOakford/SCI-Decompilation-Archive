;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include sci.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	pentagonFront 0
)

(instance pentagonFront of Rm
	(properties
		picture 18
		north 19
	)
	
	(method (init)
		(super init:)
		(self setRegions: 302)
		(LoadMany 128 18)
		(if (== prevRoomNum 19)
			(keyDownHandler add: self)
			(mouseDownHandler add: self)
			(globalSound
				number: (SoundFX 70)
				priority: 1
				loop: 1
				play:
			)
			(curRoom setScript: departScript)
		else
			(globalSound
				number: (SoundFX 67)
				priority: 1
				loop: -1
				play:
			)
			(curRoom setScript: arriveScript)
		)
		(addToPics add: cars1Pic cars2Pic jimsCarPic doit:)
	)
	
	(method (dispose)
		(if (keyDownHandler contains: self)
			(keyDownHandler delete: self)
		)
		(if (mouseDownHandler contains: self)
			(mouseDownHandler delete: self)
		)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/building,pentagon]') (Print 18 0))
			((Said 'look[<at]/driver,man') (Print 18 1))
			((Said 'look[<at]/car') (Print 18 2))
			((Said 'address') (Print 18 3))
		)
	)
	
	(method (cue)
		(self newRoom: 19)
	)
)

(instance arriveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(littleLimo
					init:
					setLoop:
					posn: 360 112
					setPri: 11
					setMotion: MoveTo 200 112 self
				)
			)
			(1
				(littleLimo xStep: 1 setMotion: MoveTo 163 112 self)
			)
			(2
				(littleLimo stopUpd:)
				(Print 18 4)
				(limoProp init: setPri: 10 setCycle: End self)
				(User canInput: 1)
			)
			(3
				(limoProp dispose:)
				(ego
					view: 18
					setLoop: 2
					posn: 174 103
					yStep: 1
					xStep: 1
					moveSpeed: 2
					cycleSpeed: 2
					init:
					setMotion: MoveTo 163 93 self
				)
			)
			(4
				(ego
					view: 204
					setLoop: -1
					moveSpeed: 0
					cycleSpeed: 0
					xStep: 3
					yStep: 2
					setCycle: Walk
				)
				(curRoom cue:)
				(HandsOn)
			)
		)
	)
)

(instance littleLimo of Act
	(properties
		view 18
		xStep 2
	)
)

(instance limoProp of Prop
	(properties
		y 110
		x 163
		view 18
		loop 1
		cycleSpeed 3
	)
	
	(method (init)
		(super init:)
		(self setPri: (+ (littleLimo priority?) 1))
	)
)

(instance departScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(littleLimo init: posn: 163 112 xStep: 2 setCel: setLoop:)
				(ego
					view: 18
					setLoop: 2
					posn: 163 93
					yStep: 1
					xStep: 1
					moveSpeed: 2
					cycleSpeed: 2
					init:
					setMotion: MoveTo 174 103 self
				)
			)
			(1
				(ego dispose:)
				(limoProp
					init:
					setCel: (limoProp lastCel:)
					setCycle: Beg self
				)
			)
			(2
				(limoProp dispose:)
				(littleLimo setPri: 11 setMotion: MoveTo -40 112 self)
			)
			(3
				(globalSound fade:)
				(curRoom newRoom: 44)
			)
		)
	)
)

(instance cars1Pic of PV
	(properties
		y 136
		x 107
		view 18
		loop 4
		priority 9
	)
)

(instance cars2Pic of PV
	(properties
		y 135
		x 263
		view 18
		loop 4
		cel 1
		priority 9
	)
)

(instance jimsCarPic of PV
	(properties
		y 169
		x 189
		view 18
		loop 3
		priority 13
	)
)

(instance limmoPic of PV
	(properties
		y 113
		x 174
		view 18
		priority 7
	)
)
