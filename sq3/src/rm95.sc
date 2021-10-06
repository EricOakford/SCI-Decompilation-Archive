;;; Sierra Script 1.0 - (do not remove this comment)
(script# 95)
(include game.sh)
(use Main)
(use Intrface)
(use Wander)
(use Follow)
(use Chase)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm95 0
)

(local
	theScore
)
(instance rm95 of Room
	(properties
		picture 95
		style WIPEUP
	)
	
	(method (init)
		(StatusLine enable:)
		(HandsOn)
		(Load VIEW 901)
		(Load VIEW 126)
		(Load VIEW 127)
		(Load VIEW 128)
		(Load VIEW 210)
		(Load VIEW 113)
		(Load VIEW 114)
		(Load VIEW 88)
		(Load VIEW 116)
		(Load VIEW 118)
		(Load SOUND 44)
		(Load SOUND 45)
		(Load SOUND 48)
		(Load SOUND 50)
		(super init:)
		(self setScript: rmScript)
	)
	
	(method (doit &tmp thisControl)
		(super doit:)
		(if (User canControl:)
			(if (>= (ego y?) 190)
				(curRoom setScript: exitHallScript)
			)
			(cond 
				((& (= thisControl (ego onControl: -1)) cRED)
					(ego setPri: 5)
					(if
						(and
							(< 120 (ego x?))
							(< (ego x?) 200)
							(>= (ego loop?) 2)
						)
						(ego
							posn: (if (< (ego x?) 160) 120 else 200) (ego y?)
						)
					)
				)
				((& thisControl cGREEN)
					(ego setPri: 4)
				)
				((& thisControl cLGREY)
					(ego setPri: 5)
				)
				((& thisControl cCYAN)
					(if (!= (bridgeFront cel?) (bridgeFront lastCel:))
						(ego setPri: 5)
					else
						(return)
					)
				)
				(else (return))
			)
			(curRoom setScript: gonner)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((or (Said 'look<down') (Said 'look/pit,deck,dirt'))
						(Print 95 0)
					)
					((Said 'look/pedestal,aisle,aisle,ramp')
						(Print 95 1)
					)
					((Said 'look/door,entrance')
						(Print 95 2)
					)
					((Said 'look/crack,crack')
						(Print 95 3)
					)
					((Said 'look/partition')
						(Print 95 4)
					)
					((or (Said 'look<up') (Said 'look/pane'))
						(Print 95 5)
					)
					((Said 'look/control,console,button')
						(Print 95 6)
					)
					((Said 'jump[/anyword]')
						(Print 95 7)
					)
					((Said 'press,press,beat,feel,use/control,console,button')
						(if (ego inRect: 0 180 320 190)
							(ego setScript: buttonScript)
						else
							(Print 95 8)
						)
					)
					((<= (captureScript state?) 3)
						(cond 
							((Said 'look[/area,left,right]')
								(Print 95 9)
							)
							((Said 'look,converse/man,folk')
								(Print 95 10)
							)
							((Said 'free,release/man[<2]')
								(Print 95 11)
							)
							((Said 'look/gel')
								(Print 95 12)
							)
							((Said 'melt/gel')
								(Print 95 13)
							)
							((Said 'eat/gel')
								(Print 95 14)
							)
							(
								(or
									(Said 'blast[/gel]')
									(Said 'use/mrgarbage')
									(Said 'use/garbage<mr')
								)
								(if (and (ego inRect: 140 102 176 110) (>= (ego loop?) 2))
									(ego setScript: captureScript)
								else
									(Print 95 15)
								)
							)
							((Said 'blast/anyword')
								(Print 95 16)
							)
							((Said 'anyword/gel')
								(if (ego inRect: 140 102 176 110)
									(Print 95 17)
								else
									(Print 95 15)
								)
							)
						)
					)
					((Said 'look/gel')
						(Print 95 18)
					)
					((Said 'blast/gel')
						(Print 95 19)
					)
					((Said 'anyword/gel')
						(Print 95 20)
					)
					((Said 'blast[/anyword]')
						(Print 95 21)
					)
					((Said 'attack,combat,escape')
						(Print 95 22)
					)
					((Said 'look[/area,left,right]')
						(Print 95 23)
						(Print 95 24)
					)
					((Said '*/man<2')
						(Print 95 25)
					)
					((Said 'look/guard')
						(Print 95 26)
					)
					((>= (captureScript state?) 16)
						(cond 
							((Said 'look/pane,man,guard,folk,accountant,eightprong')
								(Print 95 27)
							)
							((Said 'look/elmo,elmo,boy,(man<little)')
								(Print 95 28)
							)
							((Said 'converse/anyword')
								(Print 95 29)
							)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(timers eachElementDo: #dispose 84)
		(super newRoom: n)
	)
)

(instance rmScript of Script
	(method (init)
		(super init: &rest)
		(mrMark init:)
		(mrScott init:)
		(doorLeft init:)
		(doorRear init:)
		(doorRight init:)
		(bridgeLeft init:)
		(bridgeFront init:)
		(bridgeRight init:)
		(windowLeft init:)
		(windowCenter init:)
		(windowRight init:)
		(elmoPug init:)
		(jelloSound init:)
		(fallSound init:)
		(doorSound init:)
		(bridgeSound init:)
		(machineSound init:)
		(ego init:)
		(addToPics add: progsLeft progsRear progsRight)
		(addToPics doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(machineSound play:)
				(ego
					view: 113
					posn: 158 190
					setCel: -1
					setLoop: -1
					ignoreActors: 0
					setStep: 3 2
					setPri: 7
					setMotion: MoveTo 158 188 self
				)
			)
			(1
				(rm95 south: 90)
				(HandsOn)
			)
		)
	)
)

(instance buttonScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(rm95 south: 0)
				(ego setMotion: MoveTo 125 188 self)
			)
			(1
				(ego setMotion: MoveTo 120 207 self)
			)
			(2
				(ego setMotion: MoveTo (- (ego x?) 1) (ego y?) self)
			)
			(3
				(if bridgeExtended
					(= bridgeExtended FALSE)
					(bridgeFront setCycle: BegLoop self)
				else
					(= bridgeExtended TRUE)
					(bridgeFront setCycle: EndLoop self)
				)
				(bridgeSound play:)
			)
			(4
				(bridgeFront stopUpd:)
				(ego setMotion: MoveTo 158 (ego y?) self)
			)
			(5
				(ego setMotion: MoveTo 158 188 self)
			)
			(6
				(rm95 south: 90)
				(HandsOn)
			)
			(else
				(self changeState: 0)
			)
		)
	)
)

(instance gonner of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(captureScript changeState: 99)
				(ego
					view: 88
					ignoreActors: TRUE
					illegalBits: 0
					setStep: 3 12
					setLoop: (ego loop?)
					setCycle: EndLoop self
				)
			)
			(1
				(rm95 south: 0)
				(ego setCycle: 0 setMotion: MoveTo (ego x?) 220 self)
				(machineSound stop:)
				(fallSound play:)
			)
			(2
				(if (!= (fallSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 4)
				else
					(self cue:)
				)
			)
			(3
				(EgoDead 901 0 0 10)
			)
		)
	)
)

(instance exitHallScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 190 self)
			)
			(1
				(HandsOn)
				(curRoom newRoom: 90)
			)
		)
	)
)

(instance captureScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(machineSound stop:)
				(jelloSound play:)
				(ego view: 114 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(if (!= (jelloSound prevSignal?) -1)
					(-- state)
					(ego setCel: 0 setCycle: EndLoop self)
				else
					(jelloSound dispose:)
					(self cue:)
				)
			)
			(2
				(ego view: 113 setCycle: Walk)
				(mrMark
					view: 126
					setPri: (ego priority?)
					setLoop: -1
					setCel: -1
					setCycle: Walk
					observeControl: (| cGREEN cCYAN cRED)
					setMotion: Wander
				)
				(Timer setCycle: self 2)
			)
			(3
				(mrScott
					view: 127
					setPri: (ego priority?)
					setLoop: -1
					setCel: -1
					setCycle: Walk
					observeControl: (| cGREEN cCYAN cRED)
					setMotion: Wander
				)
				(= theScore score)
				(= score possibleScore)
				(theGame changeScore: -1)
				(HandsOn)
				(Timer setCycle: self 1)
			)
			(4
				(Print 95 30)
				(Print 95 31 #at -1 20 #width 280)
				(Print 95 32)
				(Print 95 33 #at -1 20 #width 280)
				(bridgeFront setCycle: BegLoop self)
				(bridgeSound play:)
			)
			(5
				(if (!= (bridgeSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 4)
				else
					(bridgeFront addToPic:)
					(self cue:)
				)
			)
			(6
				(Timer setReal: self 10)
			)
			(7
				(Print 95 34 #at -1 20 #width 280)
				(doorSound play:)
				(doorRear setCycle: EndLoop self)
			)
			(8
				(if (!= (doorSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 4)
				else
					(doorRear stopUpd:)
					(self cue:)
				)
			)
			(9
				(doorSound play:)
				(doorLeft setCycle: EndLoop self)
			)
			(10
				(if (!= (doorSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 4)
				else
					(doorLeft stopUpd:)
					(self cue:)
				)
			)
			(11
				(guardLeft init:)
				(guardLeft setMotion: MoveTo 49 110 self)
			)
			(12
				(guardLeft stopUpd:)
				(doorSound play:)
				(doorRight setCycle: EndLoop self)
			)
			(13
				(if (!= (doorSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 4)
				else
					(doorRight stopUpd:)
					(self cue:)
				)
			)
			(14
				(guardRight init:)
				(guardRight setMotion: MoveTo 271 110 self)
			)
			(15
				(guardRight stopUpd:)
				(windowLeft dispose:)
				(windowCenter dispose:)
				(windowRight dispose:)
				(Timer setReal: self 3)
			)
			(16
				(mrMark setLoop: 3 setCel: 0 setMotion: 0)
				(mrScott setLoop: 3 setCel: 0 setMotion: 0)
				(elmoPug setCycle: Forward)
				(Timer setReal: self 5)
			)
			(17
				(elmoPug setCel: 1 forceUpd:)
				(Print 95 35 #at -1 130 #width 280)
				(Print 95 36 #at -1 130 #width 280)
				(Print 95 37 #at -1 130 #width 280)
				(elmoPug setCel: 0 addToPic:)
				(HandsOff)
				(= score theScore)
				(theGame changeScore: 10)
				(fallSound dispose:)
				(bridgeLeft setCycle: EndLoop self)
				(bridgeSound play:)
			)
			(18
				(if (!= (bridgeSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 4)
				else
					(bridgeLeft addToPic:)
					(self cue:)
				)
			)
			(19
				(doorSound play:)
				(doorLeft setCycle: BegLoop self)
			)
			(20
				(if (!= (doorSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 4)
				else
					(doorLeft addToPic:)
					(self cue:)
				)
			)
			(21
				(guardLeft setMotion: MoveTo 90 110 self)
			)
			(22
				(mrMark
					setLoop: -1
					setCel: -1
					ignoreActors: 1
					ignoreControl: -1
					setMotion: Follow ego 2
				)
				(mrScott
					setLoop: -1
					setCel: -1
					ignoreActors: TRUE
					ignoreControl: -1
					setMotion: Follow mrMark 2
				)
				(guardLeft setMotion: Follow ego 30)
				(bridgeRight setCycle: EndLoop self)
				(bridgeSound play:)
			)
			(23
				(if (!= (bridgeSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 4)
				else
					(bridgeRight addToPic:)
					(self cue:)
				)
			)
			(24
				(ego
					setPri: 6
					ignoreActors: TRUE
					setMotion: MoveTo 205 110 self
				)
				(mrMark setPri: 6)
				(mrScott setPri: 6)
			)
			(25
				(guardRight setLoop: 1 setMotion: MoveTo 320 110)
				(ego setMotion: Follow guardRight 0)
				(guardLeft setMotion: Chase ego 0 self)
			)
			(26
				(guardRight dispose:)
				(guardLeft dispose:)
				(mrMark dispose:)
				(mrScott dispose:)
				(doorSound play:)
				(doorRight setCycle: BegLoop self)
			)
			(27
				(if (!= (doorSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 4)
				else
					(doorRight addToPic:)
					(self cue:)
				)
			)
			(28
				(doorSound play:)
				(doorRear setCycle: BegLoop self)
			)
			(29
				(if (!= (doorSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 4)
				else
					(doorRear addToPic:)
					(doorSound dispose:)
					(self cue:)
				)
			)
			(30
				(windowLeft init:)
				(windowCenter init:)
				(windowRight init:)
				(Timer setReal: self 1)
			)
			(31
				(ego put: 12 -1 put: 13 -1 put: 11 -1 put: 15 -1)
				(curRoom newRoom: 121)
			)
		)
	)
)

(instance bridgeLeft of Prop
	(method (init)
		(super init:)
		(self
			setPri: 4
			view: 118
			loop: 1
			cel: 0
			posn: 84 121
			ignoreActors: TRUE
			stopUpd:
		)
	)
)

(instance bridgeFront of Prop
	(method (init)
		(super init:)
		(self
			cycleSpeed: 1
			setPri: 6
			view: 118
			loop: 0
			cel: (if bridgeExtended 6 else 0)
			posn: 160 180
			ignoreActors: TRUE
			stopUpd:
		)
	)
)

(instance bridgeRight of Prop
	(method (init)
		(super init:)
		(self
			setPri: 4
			view: 118
			loop: 2
			cel: 0
			posn: 233 121
			ignoreActors: TRUE
			stopUpd:
		)
	)
)

(instance doorLeft of Prop
	(method (init)
		(super init:)
		(self
			cycleSpeed: 1
			setPri: 3
			view: 118
			loop: 4
			cel: 0
			posn: 54 125
			ignoreActors: TRUE
			stopUpd:
		)
	)
)

(instance doorRear of Prop
	(method (init)
		(super init:)
		(self
			cycleSpeed: 1
			setPri: 3
			view: 118
			loop: 3
			cel: 0
			posn: 159 84
			ignoreActors: TRUE
			stopUpd:
		)
	)
)

(instance doorRight of Prop
	(method (init)
		(super init:)
		(self
			cycleSpeed: 1
			setPri: 3
			view: 118
			loop: 5
			cel: 0
			posn: 266 124
			ignoreActors: TRUE
			stopUpd:
		)
	)
)

(instance windowLeft of Prop
	(method (init)
		(super init:)
		(self
			setPri: 5
			view: 116
			loop: 0
			cel: 0
			posn: 80 32
			stopUpd:
		)
	)
)

(instance windowCenter of Prop
	(method (init)
		(super init:)
		(self
			setPri: 5
			view: 116
			loop: 0
			cel: 1
			posn: 160 26
			stopUpd:
		)
	)
)

(instance windowRight of Prop
	(method (init)
		(super init:)
		(self
			setPri: 5
			view: 116
			loop: 0
			cel: 2
			posn: 239 32
			stopUpd:
		)
	)
)

(instance progsLeft of PicView
	(properties
		y 30
		x 61
		view 116
		loop 1
	)
)

(instance progsRear of PicView
	(properties
		y 21
		x 152
		view 116
		loop 1
		cel 1
	)
)

(instance progsRight of PicView
	(properties
		y 29
		x 251
		view 116
		loop 1
		cel 2
	)
)

(instance guardLeft of Actor
	(method (init)
		(super init:)
		(self
			ignoreActors: TRUE
			ignoreControl: cBLACK
			setPri: 6
			view: 210
			setLoop: 1
			cel: 0
			posn: 10 110
			setCycle: Walk
		)
	)
)

(instance elmoPug of Prop
	(method (init)
		(super init:)
		(self
			setPri: 4
			view: 116
			loop: 2
			cel: 0
			posn: 156 27
			stopUpd:
		)
	)
)

(instance guardRight of Actor
	(method (init)
		(super init:)
		(self
			ignoreActors: TRUE
			ignoreControl: cBLACK
			setPri: 6
			view: 210
			setLoop: 0
			cel: 0
			posn: 300 110
			setCycle: Walk
		)
	)
)

(instance mrMark of Actor
	(method (init)
		(super init:)
		(self
			view: 128
			setLoop: 0
			setCel: 0
			posn: 148 106
			setPri: 7
			setCycle: Forward
		)
	)
)

(instance mrScott of Actor
	(method (init)
		(super init:)
		(self
			view: 128
			setLoop: 1
			setCel: 2
			posn: 169 106
			setPri: 7
			setCycle: Forward
		)
	)
)

(instance jelloSound of Sound
	(properties
		number 44
		priority 1
	)
)

(instance fallSound of Sound
	(properties
		number 45
		priority 2
	)
)

(instance doorSound of Sound
	(properties
		number 48
		priority 1
	)
)

(instance bridgeSound of Sound
	(properties
		number 48
		priority 1
	)
)

(instance machineSound of Sound
	(properties
		number 50
		loop -1
	)
)
