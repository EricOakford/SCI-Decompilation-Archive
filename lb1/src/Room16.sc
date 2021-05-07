;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
(include sci.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room16 0
)
(synonyms
	(ignite lamp)
)

(local
	local0
	local1
	local2
)
(instance Room16 of Rm
	(properties
		picture 16
	)
	
	(method (init)
		(= south 22)
		(= west 15)
		(= east 17)
		(= north 37)
		(= horizon 115)
		(super init:)
		(if howFast
			(Lamp setCycle: Fwd init:)
			(Lamp2 setCycle: Fwd init:)
		else
			(Lamp addToPic:)
			(Lamp2 addToPic:)
		)
		(addToPics add: Knocker eachElementDo: #init doit:)
		(self
			setRegions: 206
			setFeatures: Knocker Window1 Window2 Mat
		)
		(LoadMany 132 43 44 128)
		(if (== currentAct 7)
			(if (!= global203 500)
				(Note
					illegalBits: 0
					ignoreActors: 1
					ignoreHorizon: 1
					init:
					stopUpd:
				)
			)
			(Rover init: setScript: bark)
		)
		(Door
			cel: (if (== prevRoomNum 37) 3 else 0)
			init:
			stopUpd:
		)
		(if (== global102 1)
			(if (== prevRoomNum 15)
				(ego posn: 1 124)
			else
				(ego posn: 318 124)
			)
		)
		(= local2 (FirstEntry))
		(switch prevRoomNum
			(37
				(HandsOff)
				(ego posn: 145 121)
				(if (not local2) (self setScript: exit))
			)
			(22 (ego posn: 160 188))
			(17
				(if global102 (ego posn: 310 126))
			)
		)
		(ego view: 0 illegalBits: -32766 init:)
	)
	
	(method (doit)
		(if local2
			(= local2 0)
			(Print 16 0)
			(if (== prevRoomNum 37) (self setScript: exit))
		)
		(if
			(and
				(== prevRoomNum 37)
				(not local1)
				(== (Door cel?) 0)
			)
			(= local1 1)
			(Door stopUpd:)
		)
		(if (< (ego y?) 140)
			(= global102 1)
		else
			(= global102 0)
		)
		(if
			(and
				(& (ego onControl: 1) $0004)
				(not local0)
				(== (ego loop?) 3)
			)
			(= local0 1)
			(self setScript: myDoor)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]') (Print 16 0))
							((Said '/stair') (Print 16 1))
							((Said '/down') (if global102 (Print 16 2) else (event claimed: 0)))
							((Said '/up') (Print 16 3))
						)
					)
					((Said 'drop/room') (= howFast 0) (event claimed: 1))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 37) (cSound stop:))
		(super newRoom: n)
	)
)

(instance myDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: 0)
				(ego loop: 3)
				(Door cycleSpeed: 1 ignoreActors: 1 setCycle: End self)
				(myMusic number: 43 loop: 1 priority: 5 play:)
			)
			(1
				(ego
					illegalBits: 0
					setMotion: MoveTo (ego x?) (- (ego y?) 50)
				)
			)
		)
	)
)

(instance bark of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 5) (== (Rover cel?) 0))
			(if (<= (DoSound sndCHECK_DRIVER) 3)
				(myBark number: 128)
			)
			(myBark loop: 1 play: self)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(cSound stop:)
				(Rover loop: 3 cycleSpeed: 1 setCycle: Fwd)
				(= cycles 12)
			)
			(2
				(Rover loop: 5 cel: 0 setCycle: End self)
			)
			(3
				(Rover loop: 6 setCycle: Fwd)
				(= cycles 12)
			)
			(4
				(Rover loop: 5 cel: 1 setCycle: Beg self)
			)
			(5
				(Rover loop: 4 setCycle: End)
			)
			(6
				(Rover setCycle: Beg)
				(if (< (Random 1 100) 40)
					(= state 4)
				else
					(= state 1)
				)
				(= cycles 1)
			)
		)
	)
)

(instance blowAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global203 500)
				(Print 16 4)
				(Note
					setLoop: 4
					setStep: 5 5
					setCycle: Fwd
					setMotion: MoveTo 330 105 self
				)
			)
			(1
				(Note dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance exit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Door setCycle: Beg self)
				(myMusic number: 44 loop: 1 priority: 5 play:)
			)
			(1
				(Door stopUpd:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance Lamp of Prop
	(properties
		y 65
		x 116
		view 116
		loop 2
		priority 14
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/ignite') (Print 16 5))
			(
			(or (MousedOn self event 3) (Said 'examine/ignite')) (event claimed: 1) (Print 16 6))
		)
	)
)

(instance Lamp2 of Prop
	(properties
		y 65
		x 204
		view 116
		loop 2
		priority 14
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 16 6)
		)
	)
)

(instance Door of Prop
	(properties
		y 116
		x 136
		view 116
		priority 8
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/door'))
			(event claimed: 1)
			(Print 16 7)
		)
	)
)

(instance Note of Act
	(properties
		y 86
		x 172
		view 116
		loop 3
	)
	
	(method (handleEvent event)
		(if (!= global203 500)
			(cond 
				((Said 'examine/door,doorknocker') (Print 16 8))
				((Said 'get/letter') (Print 16 9))
				(
					(or
						(Said 'read,examine/letter')
						(MousedOn self event 3)
					)
					(event claimed: 1)
					(if (ego inRect: 158 115 186 126)
						(Print 16 10)
						(self setScript: blowAway)
					else
						(NotClose)
					)
				)
			)
		)
	)
)

(instance Rover of Act
	(properties
		y 120
		x 112
		view 520
		loop 4
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'pat,get,move/beauregard') (Print 16 11))
			((Said 'feed/beauregard') (Print 16 12))
			(
				(or
					(Said 'deliver,feed,hold/*[/beauregard]')
					(Said 'deliver,feed,hold/*<beauregard')
				)
				(if (and theInvItem haveInvItem)
					(Print 16 12)
				else
					(DontHave)
				)
			)
			((Said 'converse,calm/beauregard') (Print 16 13))
			(
			(or (MousedOn self event 3) (Said '*/beauregard')) (event claimed: 1) (Print 16 14))
		)
	)
)

(instance Knocker of RPicView
	(properties
		y 86
		x 172
		view 116
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'what/doorknocker<big') (Print 16 15))
			(
				(or
					(Said 'bang/[<door,doorknocker]')
					(Said 'use/doorknocker[<door]')
				)
				(Print 16 16)
			)
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/doorknocker')
				)
				(event claimed: 1)
				(Print 16 17)
			)
		)
	)
)

(instance myBark of Sound
	(properties
		number 32
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 36
		nsLeft 34
		nsBottom 113
		nsRight 66
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {window})
		)
	)
)

(instance Window2 of RFeature
	(properties
		nsTop 36
		nsLeft 253
		nsBottom 113
		nsRight 285
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {window})
		)
	)
)

(instance Mat of RFeature
	(properties
		nsTop 120
		nsLeft 139
		nsBottom 124
		nsRight 178
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'move,get/doormat,(door<doormat)') (Print 16 18))
			(
			(Said 'lift,(examine<below)/doormat,(door<doormat)') (Print 16 19))
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/doormat,(door<doormat)')
				)
				(event claimed: 1)
				(Print 16 20)
			)
		)
	)
)

(instance Wind of Sound
	(properties)
)

(instance myMusic of Sound
	(properties)
)
