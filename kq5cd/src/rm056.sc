;;; Sierra Script 1.0 - (do not remove this comment)
(script# 56)
(include game.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use CDActor)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm056 0
)

(local
	[local0 2]
	local2
	local3
	[local4 7] = [3 0 2 2 1 3 3]
	[local11 26] = [0 189 0 0 319 0 319 189 201 189 178 161 261 160 178 160 155 102 138 160 51 160 139 161 116 189]
)
(instance rm056 of KQ5Room
	(properties
		picture 56
		north 57
		south 55
	)
	
	(method (init)
		(super init:)
		(theMusic number: 836 loop: -1 playBed:)
		(HandsOff)
		(= inCartoon 0)
		(ego illegalBits: 0 init:)
		(switch prevRoomNum
			(north
				(curRoom setScript: enterNorth)
			)
			(else 
				(ego setStep: 3 2 posn: 156 186 0 setPri: 14)
				(curRoom setScript: toPlatform)
			)
		)
		(addToPics add: cup doit:)
		(self setFeatures: KDoor stairs cup shelves supplyRoom)
		(if (== ((inventory at: 24) owner?) 56)
			(bag init: stopUpd:)
		)
		(fire setCycle: Forward init:)
		(cupboard init: stopUpd:)
		(poly1 points: @local11 size: 13)
		(self addObstacle: poly1)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			(script (script doit:))
			((& (= temp1 (ego onControl: 0)) $4000) (theMusic fade:) (curRoom newRoom: 57))
			((& temp1 $2000) (HandsOff) (self setScript: toStair))
		)
	)
	
	(method (dispose)
		(ego illegalBits: -32768)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance toPlatform of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 157 157 self)
			)
			(1
				(ego
					looper: duneLooper
					setMotion: MoveTo (ego x?) 161 self
					setPri: -1
					setLoop: 3
					yStep: 1
				)
				((ego head?) show:)
			)
			(2
				(ego setLoop: -1)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance toStair of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					looper: MyLooper
					setMotion: MoveTo (ego x?) 161 self
					setPri: 14
					yStep: 2
				)
			)
			(1
				(ego setStep: 3 2 setMotion: MoveTo 155 171 self)
			)
			(2
				(ego setMotion: MoveTo 157 189 self)
			)
			(3
				(theMusic fade:)
				(HandsOn)
				(curRoom newRoom: 55)
			)
		)
	)
)

(instance enterNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					posn: 156 171
					setLoop: 2
					setMotion: MoveTo 156 159 self
				)
			)
			(1
				(HandsOn)
				(ego looper: duneLooper setStep: 3 1)
				(client setScript: 0)
			)
		)
	)
)

(instance duneLooper of Script
	(properties)
	
	(method (doit)
		(if
		(!= local3 (- 164 (- ((ego mover?) y?) 164)))
			(= local3 ((ego mover?) y?))
			((ego mover?) y: (- 164 (- ((ego mover?) y?) 164)))
		)
		(ego loop: [local4 (/ (ego heading?) 60)])
	)
)

(instance openCupboard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 69 162 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 680
					setLoop: 3
					cel: 0
					cycleSpeed: 1
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(ego setCycle: EndLoop)
				(cupboard setCycle: EndLoop self)
				(theAudio number: 8796 loop: 1 play:)
			)
			(3
				(cupboard stopUpd:)
				(SpeakAudio 597)
				(bCel1 init: stopUpd:)
				(bCel2 init: stopUpd:)
				(ego
					normal: 1
					view: 0
					setLoop: -1
					loop: 7
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(4
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getPeas of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 70 161 self)
			)
			(1
				(SpeakAudio 598)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 680
					setLoop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(ego get: 24)
				(SolvePuzzle 2)
				(-- local2)
				(bag dispose:)
				(bCel2 dispose:)
			)
			(2
				(cupboard setCycle: BegLoop self)
				(theAudio number: 8795 loop: 1 play:)
			)
			(3
				(cupboard stopUpd:)
				((ego head?) show:)
				(= cycles 1)
			)
			(4
				(bCel1 dispose:)
				(ego
					normal: 1
					view: 0
					setLoop: -1
					loop: 7
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				(= cycles 3)
			)
			(5
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance supplyRoom of RFeature
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
				(verbLook
					(SpeakAudio 591)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance KDoor of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0400))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 592)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance shelves of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0008))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 593)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 599)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance stairs of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(and
					(not (& (OnControl 4 (event x?) (event y?)) $0010))
					(not (& (OnControl 4 (event x?) (event y?)) $0040))
				)
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 591)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cupboard of Prop
	(properties
		x 51
		y 142
		view 680
		signal $0001
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
				(verbLook
					(SpeakAudio 594)
					(event claimed: 1)
				)
				(verbDo
					(cond 
						((!= ((inventory at: 24) owner?) 56) (SpeakAudio 600))
						((not local2) (++ local2) (HandsOff) (curRoom setScript: openCupboard))
						((== ((inventory at: 24) owner?) 56) (SpeakAudio 601))
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bag of Prop
	(properties
		x 55
		y 125
		view 680
		loop 1
		priority 2
		signal $0011
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
				(not local2)
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 595)
					(event claimed: 1)
				)
				(verbDo
					(HandsOff)
					(curRoom setScript: getPeas)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cup of RPicView
	(properties
		x 51
		y 142
		view 680
		loop 1
		cel 1
		priority 1
	)
)

(instance fire of Prop
	(properties
		x 169
		y 156
		view 680
		loop 5
		priority 1
		signal $4010
		cycleSpeed 2
		detailLevel 3
	)
)

(instance bCel1 of Prop
	(properties
		x 55
		y 91
		view 680
		loop 4
		priority 1
		signal $0011
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
				(verbLook
					(SpeakAudio 596)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bCel2 of Prop
	(properties
		x 34
		y 75
		view 680
		loop 4
		cel 1
		priority 2
		signal $0011
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
				(verbLook
					(SpeakAudio 594)
					(event claimed: 1)
				)
				(verbDo
					(if (== ((inventory at: 24) owner?) 56)
						(HandsOff)
						(curRoom setScript: getPeas)
					else
						(SpeakAudio 600)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)
