;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include game.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm023 0
)

(local
	[local0 4]
	local4
	local5
	local6
	[local7 28] = [109 138 104 155 0 155 0 0 319 0 319 155 158 155 162 141 214 141 239 126 158 120 149 125 140 129 120 133]
)
(instance rm023 of KQ5Room
	(properties
		picture 23
	)
	
	(method (init)
		(super init:)
		(theMusic number: 42 loop: -1 playBed:)
		(self
			setFeatures: dresser burner stairs interior
			addObstacle: poly1
		)
		(if (Btst 76) (trunk cel: 3))
		(trunk init: stopUpd:)
		(if (Btst 75) (drawer cel: 3))
		(drawer init: stopUpd:)
		(if (== ((inventory at: 1) owner?) 23) (glint init:))
		(ego
			view: 0
			illegalBits: -32768
			normal: 1
			setCycle: KQ5SyncWalk
			setLoop: -1
			setStep: 3 2
			posn: 147 210
			init:
		)
		(poly1 points: @local7 size: 14)
		(self setScript: walkInScript)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((& (ego onControl: 0) $4000) (HandsOff) (self setScript: leaveRoomScript))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(theMusic fade:)
	)
)

(instance openTrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 188 127 self)
			)
			(1
				(if (not (trunk cel?))
					((ego head?) hide:)
					(ego
						normal: 0
						view: 464
						loop: 0
						cel: 0
						cycleSpeed: 2
						setCycle: EndLoop self
					)
				else
					(self cue:)
				)
			)
			(2
				(if (not (trunk cel?))
					(ego loop: 2 cel: 0 setCycle: EndLoop self)
					(trunk cycleSpeed: 2 setCycle: EndLoop self)
					(theAudio number: 8124 loop: 1 play:)
					(Bset 76)
				else
					(= state (+ state 1))
					(self cue:)
				)
			)
			(3 0)
			(4
				(if (== ((inventory at: 15) owner?) 23)
					(wheel init: stopUpd:)
					(SpeakAudio 362)
				)
				(ego
					normal: 1
					view: 0
					loop: 7
					cel: 3
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(5
				(trunk stopUpd:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getWheel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 188 127 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 464
					loop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(SpeakAudio 366)
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(wheel dispose:)
				(ego get: 15)
				(SolvePuzzle 2)
				(trunk setCycle: BegLoop self)
				(theAudio number: 8124 loop: 1 play:)
				(Bclr 76)
			)
			(4
				(ego
					normal: 1
					view: 0
					loop: 7
					cel: 3
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(5
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance openDrawer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 145 131 self)
			)
			(1
				(if (not (drawer cel?))
					((ego head?) hide:)
					(ego
						normal: 0
						view: 464
						loop: 4
						cel: 0
						cycleSpeed: 2
						setCycle: EndLoop self
					)
					(drawer setCycle: EndLoop self)
					(theAudio number: 8793 loop: 1 play:)
					(Bset 75)
				else
					(= state (+ state 1))
					(self cue:)
				)
			)
			(2 0)
			(3
				(if (== ((inventory at: 13) owner?) 23)
					(pouch init: stopUpd:)
					(SpeakAudio 365)
				)
				(ego
					normal: 1
					view: 0
					loop: 7
					cel: 1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(4
				(drawer stopUpd:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getPouch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 145 131 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 464
					loop: 4
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(SpeakAudio 9052)
				(ego loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(3
				(pouch dispose:)
				(ego get: 13)
				(SolvePuzzle 2)
				(drawer setCycle: BegLoop self)
				(theAudio number: 8793 loop: 1 play:)
				(Bclr 75)
			)
			(4
				(ego
					normal: 1
					view: 0
					loop: 7
					cel: 1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(5
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getKey of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 181 134 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 464
					loop: 8
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(SpeakAudio 9053)
				(glint dispose:)
				(SolvePuzzle 2)
				(ego get: 1 setCycle: BegLoop self)
			)
			(3
				(ego
					normal: 1
					view: 0
					loop: 11
					cel: 6
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

(instance walkInScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 152 143 self)
			)
			(1
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance leaveRoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 142 200 self)
			)
			(1
				(HandsOn)
				(curRoom newRoom: 22)
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
				(not (& (OnControl 4 (event x?) (event y?)) $0040))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 358)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 367)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance dresser of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0010))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 359)
					(event claimed: 1)
				)
				(verbDo
					(cond 
						(
						(and (not local5) (== ((inventory at: 13) owner?) 23))
							(++ local5)
							(HandsOff)
							(curRoom setScript: openDrawer)
							(event claimed: 1)
						)
						((== ((inventory at: 13) owner?) 23)
							(HandsOff)
							(curRoom setScript: getPouch)
							(event claimed: 1)
						)
						(else (SpeakAudio 368) (event claimed: 1))
					)
				)
			)
		)
	)
)

(instance interior of RFeature
	(properties
		nsTop 23
		nsLeft 51
		nsBottom 160
		nsRight 269
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
					(SpeakAudio 360)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance trunk of Prop
	(properties
		x 199
		y 120
		view 464
		loop 1
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
					(SpeakAudio 361)
					(event claimed: 1)
				)
				(verbDo
					(cond 
						(
						(and (not local4) (== ((inventory at: 15) owner?) 23))
							(++ local4)
							(HandsOff)
							(curRoom setScript: openTrunk)
							(event claimed: 1)
						)
						((== ((inventory at: 15) owner?) 23)
							(HandsOff)
							(curRoom setScript: getWheel)
							(event claimed: 1)
						)
						(else (SpeakAudio 369) (event claimed: 1))
					)
				)
			)
		)
	)
)

(instance wheel of Prop
	(properties
		x 229
		y 88
		view 462
		cel 1
	)
	
	(method (doit)
		(super doit:)
		(if (ego mover?) (self dispose:))
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
					(SpeakAudio 362)
					(event claimed: 1)
				)
				(verbDo
					(HandsOff)
					(curRoom setScript: getWheel)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance burner of RFeature
	(properties
		nsTop 75
		nsLeft 188
		nsBottom 90
		nsRight 199
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
					(if (== ((inventory at: 1) owner?) 23)
						(SpeakAudio 363)
					else
						(SpeakAudio 364)
					)
					(event claimed: 1)
				)
				(verbDo
					(if (== ((inventory at: 1) owner?) 23)
						(HandsOff)
						(curRoom setScript: getKey)
						(event claimed: 1)
					else
						(SpeakAudio 370)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance drawer of Prop
	(properties
		x 129
		y 108
		view 464
		loop 7
		cycleSpeed 2
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
					(SpeakAudio 359)
					(event claimed: 1)
				)
				(verbDo
					(cond 
						((== (drawer cel?) 0)
							(HandsOff)
							(curRoom setScript: openDrawer)
							(event claimed: 1)
						)
						((== ((inventory at: 13) owner?) 23)
							(HandsOff)
							(curRoom setScript: getPouch)
							(event claimed: 1)
						)
						((not local5) (SpeakAudio 368) (++ local5) (event claimed: 1))
					)
				)
			)
		)
	)
)

(instance pouch of Prop
	(properties
		x 129
		y 88
		view 462
	)
	
	(method (doit)
		(super doit:)
		(if (ego mover?) (self dispose:))
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
					(SpeakAudio 365)
					(event claimed: 1)
				)
				(verbDo
					(HandsOff)
					(curRoom setScript: getPouch)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance glint of Prop
	(properties
		x 192
		y 83
		view 462
		loop 1
		signal $4000
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(local6 (-- local6))
			((not (Random 0 40)) (self setCycle: BegLoop) (= local6 (Random 50 150)))
		)
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)
