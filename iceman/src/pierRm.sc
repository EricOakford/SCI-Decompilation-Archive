;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include sci.sh)
(use Main)
(use Intrface)
(use InitAllFeatures)
(use LoadMany)
(use Wander)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	pierRm 0
)

(local
	local0
	local1
	local2
	local3
	local4
	[local5 11]
)
(procedure (localproc_02d2 param1)
	(return (== (param1 onControl: 1) 16384))
)

(instance pierRm of Rm
	(properties
		picture 23
	)
	
	(method (init)
		(super init:)
		(LoadMany 128 123 23 423 23 523)
		(ego
			view: 123
			loop: 0
			ignoreControl: 16384
			ignoreControl: 16
			observeControl: -32768
			posn: 20 145
			init:
			setScript: egoControlScript
		)
		(officer init: setLoop:)
		(flag init:)
		(wave init:)
		(wave2 init:)
		(harborSound play:)
		((Clone bird) init:)
		(bird init:)
		(addToPics add: hatch eachElementDo: #init doit:)
		(InitAllFeatures)
		(self setScript: waveScript setFeatures: flagFeat)
	)
	
	(method (dispose)
		(ego cycleSpeed: 0)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look>')
				(cond 
					(
					(Said '[<around,at][/room,sub,sub,boat,blackhawk]') (Print 23 0))
					((Said '<up') (SeeNothing))
					((Said '<down') (Print 23 1))
					((Said '<man,officer') (Print 23 2))
					(
					(Said '/entrance,bridge,walkway,deck,pier,gangplank') (Print 23 3))
					((Said '/crane') (Print 23 4))
				)
			)
			(
				(or
					(Said 'come<aboard<ask<permission<to')
					(Said 'ask/permission/board')
				)
				(if (& (ego onControl:) $4000)
					(NotClose)
				else
					(= local3 2)
					(curRoom setScript: comeAboardScript)
				)
			)
			((Said 'address[/man]')
				(if (& (ego onControl:) $4000)
					(NotClose)
				else
					(++ local3)
					(curRoom setScript: comeAboardScript)
				)
			)
			((Said 'board/boat,sub') (Print 23 5))
		)
	)
)

(instance harborSound of Sound
	(properties
		number 68
		loop -1
	)
)

(instance egoControlScript of Script
	(properties)
	
	(method (doit)
		(if
		(or (== (ego onControl: 1) 16) (Said 'walk/plank'))
			(ego setMotion: 0 setScript: walkUpPlankScript)
		)
	)
)

(instance officer of Act
	(properties
		y 115
		x 156
		view 523
		priority 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/officer,man]>')
				(cond 
					((Said 'look[<at]') (Print 23 2))
					((Said 'salute')
						(cond 
							(local1 (Print 23 6))
							(
								(or
									(> (ego distanceTo: self) 15)
									(< (ego distanceTo: self) 5)
								)
								(Print 23 7)
							)
							(else
								(if (== (++ local4) 2) (++ local4))
								(Print 23 8 #at -1 120 #dispose)
								(User canInput: 0)
								(ego setScript: saluteOfficerScript)
							)
						)
					)
				)
			)
			((localproc_02d2 ego))
			((Said 'come<aboard<ask<permission<to')
				(if (& (ego onControl:) $4000)
					(NotClose)
				else
					(= local3 2)
					(curRoom setScript: comeAboardScript)
				)
			)
			((Said 'show/order')
				(if (& (ego onControl:) $4000)
					(NotClose)
				else
					(++ local3)
					(curRoom setScript: comeAboardScript)
				)
			)
			((Said 'address[/man]')
				(if (& (ego onControl:) $4000)
					(NotClose)
				else
					(++ local3)
					(curRoom setScript: comeAboardScript)
				)
			)
		)
	)
)

(instance walkUpPlankScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 43 140 self
				)
			)
			(1
				(ego setMotion: MoveTo 161 116 self)
			)
			(2
				(User canInput: 1 canControl: 0)
			)
		)
	)
)

(instance comeAboardScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: 0)
				(ego heading: 0 setLoop: -1)
				((ego looper?) doit: ego 0 self)
			)
			(1
				(if (> local3 1)
					(= cycles 3)
				else
					(Print 23 9)
					(User canInput: 1)
				)
			)
			(2
				((ego looper?) caller: 0)
				(Print 23 10 #at 25 10)
				(Print 23 11 #at 25 10)
				(Print 23 12 #at 25 10)
				(Print 23 13 #at 25 10)
				(ego put: 0)
				(= cycles 1)
			)
			(3
				(Print 23 14 #at 25 10)
				(Print 23 15 #at 25 10)
				(= seconds 1)
			)
			(4
				(Print 23 16 #at 25 10)
				(= cycles 5)
			)
			(5
				(ego setScript: downTheHatchScript self)
			)
			(6
				(HandsOn)
				(if (== local4 3)
					(Print 23 17)
					(theGame changeScore: 3)
				)
				(curRoom newRoom: 31)
			)
		)
	)
)

(instance saluteFlagScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: -1 heading: 90)
				((ego looper?) doit: ego (ego heading?) self)
			)
			(1
				(ego
					view: 23
					setLoop: 1
					setCel: 0
					cycleSpeed: 0
					setCycle: End self
				)
			)
			(2
				(Print 23 18 #at -1 120 #dispose)
				(= seconds 3)
			)
			(3 (ego setCycle: Beg self))
			(4
				(cls)
				(ego view: 123 cycleSpeed: 0 setLoop: 0 setCel: 16)
				(= local0 1)
				(= cycles 2)
			)
			(5
				(User canInput: 1)
				(self dispose:)
			)
		)
	)
)

(instance saluteOfficerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego heading: 0 cel: 2 setLoop: -1)
				((ego looper?) doit: ego (ego heading?) self)
			)
			(1
				(ego
					view: 23
					setLoop: 2
					setCel: 0
					cycleSpeed: 0
					setCycle: End self
				)
			)
			(2 (= seconds 3))
			(3
				(cls)
				(ego setCycle: Beg self)
				(= local1 1)
			)
			(4
				(ego
					view: 123
					setLoop: 3
					cycleSpeed: 0
					cel: 2
					setCycle: Walk
				)
				(User canInput: 1)
			)
		)
	)
)

(instance downTheHatchScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 123
					setCycle: Walk
					setLoop: -1
					illegalBits: 0
					setMotion: MoveTo 206 121 self
				)
			)
			(1
				(ego setAvoider: 0 setLoop: -1 heading: 270)
				(harborSound fade:)
				((ego looper?) doit: ego (ego heading?) self)
			)
			(2
				(ego
					view: 23
					loop: 4
					setPri: 10
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(3 (ego hide:) (= cycles 5))
			(4 (self dispose:))
		)
	)
)

(instance wave of Prop
	(properties
		y 189
		x 61
		view 423
		priority 14
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
	)
	
	(method (handleEvent)
		(if (Said 'look[<at]/wave') (Print 23 19))
	)
	
	(method (addToPic)
		(wave2 addToPic:)
		(waveScript register: 1)
		(super addToPic:)
	)
)

(instance wave2 of Prop
	(properties
		y 189
		x 181
		view 423
		loop 1
		priority 14
		cycleSpeed 2
	)
	
	(method (handleEvent)
		(if (Said 'look[<at]/wave') (Print 23 19))
	)
)

(instance waveScript of Script
	(properties)
	
	(method (init)
		(if register (self dispose:) else (super init: &rest))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wave cel: 0 setCycle: End self)
				(wave2 cel: 0 setCycle: End self)
			)
			(1)
			(2
				(wave stopUpd:)
				(wave2 stopUpd:)
				(= cycles (Random 10 30))
			)
			(3 (self init:))
		)
	)
)

(instance flag of Prop
	(properties
		y 42
		x 267
		view 23
		cel 2
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: End self isExtra: 1)
	)
	
	(method (addToPic)
		(self cel: 8)
		(super addToPic:)
	)
	
	(method (cue)
		(self setCycle: Fwd)
		(flagFeat nsLeft: nsLeft)
		(flagFeat nsRight: nsRight)
		(flagFeat nsTop: nsTop)
		(flagFeat nsBottom: nsBottom)
		(super cue:)
	)
)

(instance flagFeat of RFeature
	(properties
		y 42
		x 267
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/man,officer') (Print 23 20))
			((Said '[/flag,ensign]>')
				(cond 
					((Said 'look[<at]') (Print 23 21))
					((Said 'salute')
						(cond 
							(local0 (Print 23 6))
							(
								(or
									(> (ego distanceTo: self) 130)
									(< (ego distanceTo: self) 115)
								)
								(Print 23 22)
							)
							(else
								(++ local4)
								(User canInput: 0)
								(ego setScript: saluteFlagScript)
							)
						)
					)
				)
			)
		)
	)
)

(instance hatch of RPicView
	(properties
		y 127
		x 238
		view 23
		loop 3
		priority 9
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/hatch]>')
				(cond 
					((Said 'look[<at]') (Print 23 23))
					((Said 'open,close') (Print 23 24))
					((or (Said 'climb<down') (Said 'down[<the]')) (ego setScript: downTheHatchScript))
				)
			)
		)
	)
)

(instance bird of Act
	(properties
		y 20
		x 100
		view 23
	)
	
	(method (init)
		(super init:)
		(self
			setMotion: Wander 100
			setCycle: Fwd
			x: (Random 50 270)
			setLoop: 5
			setPri: 0
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look/bird') (Print 23 25))
		)
	)
	
	(method (canBeHere)
		(return
			(if (and (< -20 y) (< y 50) (< -50 x))
				(< x 370)
			else
				0
			)
		)
	)
)
