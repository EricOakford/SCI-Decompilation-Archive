;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm380 0
)
(synonyms
	(use class do)
)

(local
	exerciseTimes
	local1
	local2
	local3
	oldEgoX
	oldEgoY
	oldEgoPriority
	poundsPumped
	legCurls
	pullUps
	barPulls
	currentActivity
	local12
)
(procedure (StartExercising activity)
	(Ok)
	(HandsOff)
	(= currentActivity activity)
	(= oldEgoX (ego x?))
	(= oldEgoY (ego y?))
	(= oldEgoPriority (ego priority?))
	(switch (++ exerciseTimes)
		(1
			(Print 380 30)
		)
		(2
			(Print 380 31)
		)
		(3
			(Print 380 32)
		)
		(4
			(Print 380 33)
		)
		(5
			(Print 380 34)
			(= exerciseTimes 0)
		)
	)
	(User mapKeyToDir: FALSE canInput: TRUE)
	(ego viewer: 0 illegalBits: 0 ignoreActors:)
)

(procedure (StopExercising)
	(= currentActivity 0)
	(= local12 0)
	(Bset fNotShower)
	(Bset fNotUseSoap)
	(Bset fNotUseDeodorant)
	(NormalEgo loopS (+ 704 larryBuffed))
	(ego
		posn: oldEgoX oldEgoY
		setPri: oldEgoPriority
		viewer: egoViewer
	)
	(aBigEgo
		cycleSpeed: 0
		posn: 1234 1234
		setMotion: 0
		stopUpd:
	)
	(if
		(and
			(>= requiredBarPulls barPulls)
			(>= requiredPullUps pullUps)
			(>= requiredPoundsPumped poundsPumped)
			(>= requiredLegCurls legCurls)
		)
		(HandsOff)
		(RoomScript changeState: 39) ;time to buff up!
	)
)

(procedure (DisplayTotalExercise &tmp [str 11])
	;this shows how much of each activity must be done
	(= local2 0)
	(Display
		(Format @str 380 35
			requiredBarPulls
			requiredPullUps
			requiredPoundsPumped
			requiredLegCurls
		)
		p_at 122 82
		p_font 999
		p_color vLRED
		p_back vBLACK
	)
)

(instance rm380 of Room
	(properties
		picture 380
		horizon 1
		east 370
	)
	
	(method (init &tmp requiredNum)
		(if (not larryBuffed)
			(Load SOUND 380)
			(Load VIEW 383)
			(Load VIEW 384)
			(Load VIEW 385)
			(Load VIEW 386)
			(Load VIEW 387)
		)
		(super init:)
		(addToPics add: atpPullupHandles doit:)
		(aBigEgo init:)
		(aActor1 init:)
		(aCenterWeight init:)
		(aRoundBar init:)
		(aExtraBar init:)
		(aDumbbell init:)
		(aBarPullBarView init:)
		(aLegCurlBar init:)
		(self setScript: RoomScript)
;		(= requiredNum (+ (/ machineSpeed 5) 5))
;		(if debugging (= requiredNum 5))
		(= requiredNum 25) ;EO: made this a flat 25 to fix an original speed bug
		(= barPulls requiredNum)
		(= pullUps requiredNum)
		(= poundsPumped requiredNum)
		(= legCurls requiredNum)
		(NormalEgo loopW (+ 704 larryBuffed))
		(ego posn: 270 175 setPri: 9 viewer: egoViewer init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBROWN)
			(curRoom newRoom: 370)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 4)
		(switch (= state newState)
			(0)
			(1
				(StartExercising 4)
				(ego
					view: 381
					posn: 201 135
					setLoop: 8
					cel: 0
					cycleSpeed: 1
				)
				(aActor1
					setStep: 3 5
					posn: 155 45
					view: 384
					setLoop: 2
					setPri: 12
				)
				(aBigEgo
					view: 384
					setLoop: 1
					setPri: 13
					posn: 155 80
					stopUpd:
				)
				(aLegCurlBar hide:)
				(self cue:)
			)
			(2
				(= local2 1)
				(= local12 3)
			)
			(3
				(= local12 1)
				(ego cel: 0 setCycle: EndLoop)
				(aActor1 setMotion: MoveTo 155 15 self)
				(aCenterWeight setMotion: MoveTo 153 136)
			)
			(4
				(= local12 4)
				(if local2
					(if (== (++ requiredLegCurls) legCurls)
						(Printf 380 17 requiredLegCurls)
					)
					(DisplayTotalExercise)
				)
			)
			(5
				(= local12 2)
				(ego setCycle: BegLoop)
				(aActor1 setMotion: MoveTo 155 50 self)
				(aCenterWeight setMotion: MoveTo 153 166)
				(= state 1)
			)
			(6)
			(7
				(User
					mapKeyToDir: TRUE
					canInput: FALSE
					canControl: FALSE
				)
				(aCenterWeight setMotion: MoveTo 153 166 self)
				(aActor1 setMotion: MoveTo 155 50 self)
			)
			(8)
			(9
				(aLegCurlBar show:)
				(aCenterWeight stopUpd:)
				(StopExercising)
			)
			(10
				(StartExercising 3)
				(ego
					cycleSpeed: 1
					view: 381
					posn: 216 163
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(11
				(aRoundBar hide:)
				(aBigEgo view: 382 cel: 0 setPri: 13 posn: 156 24)
				(aDumbbell hide:)
				(aActor1
					view: 380
					setLoop: 0
					setStep: 7 7
					setPri: 14
					posn: 155 34
				)
				(ego setPri: 13 setLoop: 2 posn: 219 159)
				(self cue:)
			)
			(12
				(= local2 1)
				(= local12 3)
			)
			(13
				(= local12 1)
				(ego cel: 0 setCycle: EndLoop self)
				(aActor1 setMotion: MoveTo 155 20)
				(aBigEgo setCycle: EndLoop)
			)
			(14
				(= local12 4)
				(if local2
					(if (== (++ requiredPoundsPumped) poundsPumped)
						(Printf 380 18 (* 100 requiredPoundsPumped))
					)
					(DisplayTotalExercise)
				)
			)
			(15
				(= local12 2)
				(ego setCycle: BegLoop self)
				(aActor1 setMotion: MoveTo 155 34)
				(aBigEgo setCycle: BegLoop)
				(= state 11)
			)
			(16)
			(17
				(User
					mapKeyToDir: TRUE
					canInput: FALSE
					canControl: FALSE
				)
				(aBigEgo posn: 1234 1234 setMotion: 0)
				(aActor1 posn: 155 34 setMotion: 0 stopUpd:)
				(aRoundBar show:)
				(aDumbbell show:)
				(ego
					posn: 216 163
					setLoop: 3
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(18
				(StopExercising)
			)
			(19
				(ego
					illegalBits: 0
					viewer: 0
					setPri: 13
					setMotion: MoveTo 156 171 self
				)
			)
			(20
				(ego
					view: 381
					posn: 156 146
					setLoop: 5
					cel: 0
					setCycle: EndLoop self
				)
			)
			(21
				(StartExercising 2)
				(ego
					posn: 165 143
					setPri: 13
					setLoop: 6
					cel: 0
					cycleSpeed: 2
				)
				(aBigEgo view: 384 setLoop: 0 posn: 302 1)
				(self cue:)
			)
			(22
				(= local2 1)
				(= local12 3)
			)
			(23
				(= local12 1)
				(ego setCycle: EndLoop)
				(aBigEgo setMotion: MoveTo 302 -52 self)
			)
			(24
				(= local12 4)
				(if local2
					(if (== (++ requiredPullUps) pullUps)
						(Printf 380 19 requiredPullUps)
					)
					(DisplayTotalExercise)
				)
			)
			(25
				(= local12 2)
				(ego setCycle: BegLoop)
				(aBigEgo setMotion: MoveTo 302 1 self)
				(= state 21)
			)
			(26)
			(27
				(User mapKeyToDir: 1)
				(= oldEgoX 156)
				(= oldEgoY 175)
				(StopExercising)
			)
			(28
				(StartExercising 1)
				(ego
					cycleSpeed: 1
					view: 381
					posn: 131 165
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
					setPri: 13
				)
			)
			(29
				(ego setLoop: 4 setCel: 0 posn: 126 165)
				(aBigEgo
					cycleSpeed: 1
					view: 383
					posn: 76 115
					setPri: 13
					cel: 0
				)
				(aActor1
					setStep: 3 5
					posn: 155 45
					view: 384
					setLoop: 2
					setPri: 12
				)
				(aExtraBar hide:)
				(aBarPullBarView hide:)
				(self cue:)
			)
			(30
				(= local2 1)
				(= local12 4)
			)
			(31
				(= local12 2)
				(ego setCycle: EndLoop)
				(aBigEgo setCycle: EndLoop)
				(aActor1 setMotion: MoveTo 155 15 self)
				(aCenterWeight setMotion: MoveTo 153 136)
			)
			(32
				(= local12 3)
				(if local2
					(if (== (++ requiredBarPulls) barPulls)
						(Printf 380 20 requiredBarPulls)
					)
					(DisplayTotalExercise)
				)
			)
			(33
				(= local12 1)
				(ego setCycle: BegLoop)
				(aBigEgo setCycle: BegLoop)
				(aActor1 setMotion: MoveTo 155 50 self)
				(aCenterWeight setMotion: MoveTo 153 166)
				(= state 29)
			)
			(34)
			(35
				(User
					mapKeyToDir: TRUE
					canInput: FALSE
					canControl: FALSE
				)
				(aCenterWeight setMotion: MoveTo 153 166 self)
				(aActor1 setMotion: MoveTo 155 50 self)
				(aBarPullBarView show: stopUpd:)
				(aExtraBar show: stopUpd:)
				(aBigEgo posn: 999 999)
				(ego posn: 131 165 setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(36)
			(37)
			(38
				(aCenterWeight stopUpd:)
				(StopExercising)
			)
			(39
				(= larryBuffed 20)
				(= local3 1)
				(= currentEgoView 720)
				(NormalEgo loopS 724)
				(HandsOff)
				(= seconds 3)
			)
			(40
				(Print 380 21)
				(music number: 380 loop: musicLoop play:)
				(= local1 0)
				(NormalEgo loopS 704)
				(HandsOff)
				(= saveSpeed (theGame setSpeed: 6))
				(= seconds 3)
			)
			(41
				(ego
					illegalBits: cBROWN
					ignoreActors:
					view: 385
					setLoop: local1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(42
				(cond 
					((== 0 local1)
						(Print 380 22)
					)
					((== 3 local1)
						(Print 380 23)
					)
				)
				(if (>= 4 (++ local1))
					(-= state 2)
				)
				(= cycles 11)
			)
			(43
				(Print 380 24)
				(ego view: 386 loop: 0 cel: 0 setCycle: EndLoop)
				(= cycles 18)
			)
			(44
				(ego loop: 1 cel: 0 setCycle: Forward)
				(= cycles 33)
			)
			(45
				(Print 380 25)
				(= cycles 33)
			)
			(46
				(ego loop: 2 cel: 0 setCycle: EndLoop)
				(= cycles 11)
			)
			(47
				(Print 380 26)
				(ego setCycle: BegLoop)
				(= cycles 11)
			)
			(48
				(ego loop: 3 cel: 0 setCycle: EndLoop)
				(= cycles 15)
			)
			(49
				(ego setCycle: BegLoop)
				(= cycles 11)
			)
			(50
				(Print 380 27)
				(ego loop: 5 cel: 0 setCycle: EndLoop)
				(= cycles 22)
			)
			(51
				(ego setCycle: BegLoop)
				(= cycles 11)
			)
			(52
				(ego loop: 4 cel: 0 setCycle: EndLoop)
				(= cycles 15)
			)
			(53
				(ego setCycle: BegLoop)
				(= cycles 11)
			)
			(54
				(ego loop: 6 cel: 0 setCycle: EndLoop)
				(= cycles 22)
			)
			(55
				(ego setCycle: BegLoop)
				(= cycles 11)
			)
			(56
				(ego view: 387 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(57
				(Print 380 28)
				(ego loop: 1 setCycle: Forward)
				(= cycles 33)
			)
			(58
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(59
				(NormalEgo loopE 724)
				(ego viewer: egoViewer)
				(theGame changeScore: 100)
				(Print 380 29)
				(music number: 399 loop: musicLoop play:)
				(theGame setSpeed: saveSpeed)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (and currentActivity (== (event type?) keyDown))
			(cond 
				(
					(or
						(== (event message?) UPARROW)
						(== (event message?) HOMEKEY)
						(== (event message?) PAGEUP)
					)
					(cond 
						((== local12 3)
							(self cue:)
						)
						((== local12 2)
							(++ state)
							(self cue:)
						)
					)
				)
				(
					(or
						(== (event message?) DOWNARROW)
						(== (event message?) ENDKEY)
						(== (event message?) PAGEDOWN)
					)
					(cond 
						((== local12 4)
							(self cue:)
						)
						((== local12 1)
							(++ state)
							(self cue:)
						)
					)
				)
			)
		)
		(if (!= (event type?) saidEvent) (return))
		(cond 
			(
				(or
					(Said 'nightstand,(nightstand<up),(get<off,up)')
					(Said '(get<off,up),exit,cease,done')
					(Said 'exit/barstool')
				)
				(switch currentActivity
					(0 (YouAre))
					(4 (RoomScript changeState: 7))
					(3 (RoomScript changeState: 17))
					(2 (RoomScript changeState: 27))
					(1 (RoomScript changeState: 35))
				)
			)
			((Said 'lie,(work<out),use')
				(cond 
					(currentActivity
						(Print 380 0)
					)
					((& (ego onControl:) cCYAN)
						(if (>= requiredLegCurls legCurls)
							(Print 380 1)
						else
							(self changeState: 1)
						)
					)
					((& (ego onControl:) cGREEN)
						(if (>= requiredPoundsPumped poundsPumped)
							(Print 380 2)
						else
							(self changeState: 10)
						)
					)
					((& (ego onControl:) cRED)
						(if (>= requiredPullUps pullUps)
							(Print 380 3)
						else
							(self changeState: 19)
						)
					)
					((& (ego onControl:) cMAGENTA)
						(if (>= requiredBarPulls barPulls)
							(Print 380 4)
						else
							(self changeState: 28)
						)
					)
					(else
						(Print 380 5)
						(Print 380 6)
					)
				)
			)
			(
				(or
					(Said 'use/equipment<drag,bar')
					(Said 'use/bar,drag')
					(Said 'use/drag<bar')
					(Said 'drag/bar')
				)
				(cond 
					(currentActivity
						(Print 380 0)
					)
					((>= requiredBarPulls barPulls)
						(Print 380 7)
					)
					((& (ego onControl:) cMAGENTA)
						(self changeState: 28)
					)
					(else
						(Print 380 8)
					)
				)
			)
			(
				(or
					(Said 'use/equipment<curl')
					(Said 'curl')
					(Said 'use/curl')
				)
				(cond 
					(currentActivity
						(Print 380 0)
					)
					((>= requiredLegCurls legCurls)
						(Print 380 9)
					)
					((& (ego onControl:) cCYAN)
						(self changeState: 1)
					)
					(else
						(Print 380 10)
					)
				)
			)
			(
				(or
					(Said 'use/equipment<drag,barstool')
					(Said 'drag,bell')
					(Said 'barstool<drag/')
					(Said 'get<on/barstool')
					(Said 'increase,use/drag,bell,bell')
				)
				(cond 
					(currentActivity
						(Print 380 0)
					)
					((>= requiredPoundsPumped poundsPumped)
						(Print 380 11)
					)
					((& (ego onControl:) cGREEN)
						(self changeState: 10)
					)
					(else
						(Print 380 12)
					)
				)
			)
			(
				(or
					(Said 'use/equipment<up,pullup,drag')
					(Said 'use/up<drag')
					(Said 'pullup')
					(Said '(up<drag)')
					(Said 'use/pullup')
				)
				(cond 
					(currentActivity
						(Print 380 0)
					)
					((>= requiredPullUps pullUps)
						(Print 380 13)
					)
					((& (ego onControl:) cRED)
						(self changeState: 19)
					)
					(else (Print 380 14))
				)
			)
			((Said 'perspiration')
				(if currentActivity
					(YouAre)
				else
					(Print 380 15)
				)
			)
			((and (Said 'look>') (Said '[/equipment,pos,area]'))
				(Print 380 16)
			)
		)
	)
)

(instance atpPullupHandles of PicView
	(properties
		y 143
		x 165
		view 380
		loop 2
		priority 9
		signal ignrAct
	)
)

(instance aRoundBar of View
	(properties
		y 142
		x 201
		view 381
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 stopUpd:)
	)
)

(instance aBarPullBarView of View
	(properties
		y 63
		x 30
		view 380
		loop 1
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 stopUpd:)
	)
)

(instance aLegCurlBar of View
	(properties
		y 164
		x 226
		view 381
		cel 2
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance aDumbbell of View
	(properties
		y 34
		x 155
		view 380
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 stopUpd:)
	)
)

(instance aExtraBar of View
	(properties
		y 138
		x 128
		view 381
		cel 1
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance aActor1 of Actor
	(properties
		y 999
		x 155
		view 380
		signal ignrAct
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreHorizon: stopUpd:)
	)
)

(instance aCenterWeight of Actor
	(properties
		y 166
		x 153
		view 381
		signal ignrAct
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setStep: 1 5 setLoop: 7 setPri: 11 stopUpd:)
	)
)

(instance aBigEgo of Actor
	(properties
		y 999
		x 156
		view 382
		signal ignrAct
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 setStep: 6 6 ignoreHorizon: stopUpd:)
	)
)

(instance egoViewer of Code
	(method (doit)
		(cond 
			((< (ego y?) 172)
				(ego setPri: 4)
			)
			((& (ego onControl:) cBLUE)
				(ego setPri: 9)
			)
			(else
				(ego setPri: -1)
			)
		)
	)
)
