;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh)
(use Main)
(use Intrface)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm013 0
)

(local
	local0
	local1
	local2
	local3
	printObj
	local5
)
(instance rm013 of Room
	(properties
		picture 13
		style HSHUTTER
		horizon 10
	)
	
	(method (init &tmp [temp0 50])
		(HandsOn)
		(Load SCRIPT JUMP)
		(= local3 991)
		(Load VIEW 17)
		(Load VIEW 26)
		(Load VIEW 752)
		(Load VIEW 852)
		(Load VIEW 10)
		(Load VIEW 289)
		(if (== motivatorState motivatorGRABBED)
			(Load VIEW 260)
			(Load VIEW 261)
		else
			(Load VIEW 22)
			(Load VIEW 23)
		)
		(Load SOUND 35)
		(Load SOUND 45)
		(Load SOUND 52)
		(Load SOUND 53)
		(if (not grabberRect) (= grabberRect 226))
		(if (!= prevRoomNum 12) (= global132 3))
		(super init:)
		(bot init:)
		(HandsOn)
		(ego setStep: -1 1)
		(if (== global132 3)
			(ego
				view: 0
				posn: 304 111
				setStep: 3
				setPri: 9
				init:
				setScript: railWalkScript
			)
			(grabber
				loop: (if (== motivatorState motivatorGRABBED) 1 else 0)
				setStep: 3 1
				posn: grabberRect 104
				init:
				stopUpd:
			)
		else
			(ego setScript: grabScript)
		)
	)
	
	(method (doit)
		(if
			(and
				(not local2)
				(or
					(ego inRect: 35 103 260 105)
					(ego inRect: 143 74 237 78)
					(== global132 6)
				)
			)
			(= local2 15)
		)
		(if
			(and
				(> local2 2)
				(not (ego inRect: 35 103 260 105))
				(not (ego inRect: 143 74 237 78))
				(!= global132 6)
			)
			(= local2 0)
		)
		(if (and (> local2 2) global219) (-- local2))
		(if
		(and (== (bot script?) botPanelScript) (== local2 2))
			(= local2 1)
			(cond 
				(
				(or (ego inRect: 35 103 260 105) (== global132 6)) (bot setScript: frontZapScript))
				((ego inRect: 143 74 237 78) (bot setScript: backZapScript))
				(else (= local2 0))
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(mouseDown
				(if
					(and
						(not isHandsOff)
						(== (User controls?) 1)
						(!= global132 6)
					)
					(ego
						setMotion:
							MoveTo
							(cond 
								((< (event x?) 30) -30)
								((> (event x?) 295) 330)
								(else (event x?))
							)
							(ego y?)
					)
					(event claimed: TRUE)
				)
			)
			(saidEvent
				(if (Said 'look>')
					(cond 
						((Said '/android') (Print 13 0))
						((Said '/comp,console,(device<monitoring)') (Print 13 1))
						((Said '/device,grabber,console[<control]')
							(if (or (== global132 4) (== global132 5))
								(Print 13 2)
							else
								(Print 13 3)
							)
						)
						((Said '/motivator,artifact')
							(if (== motivatorState 3)
								(if (or (== global132 4) (== global132 5))
									(Print 13 4)
								else
									(Print 13 5)
								)
							else
								(Print 13 6)
							)
						)
						((Said 'look/throttle,control,stick')
							(if (or (== global132 4) (== global132 5))
								(Print 13 7)
							else
								(Print 13 8)
							)
						)
						((Said '/claw[<grabber]') (if (== global132 6) (Print 13 9) else (Print 13 4)))
						((Said '/chair[<grabber,device]')
							(if (or (== global132 4) (== global132 5))
								(Print 13 10)
							else
								(Print 13 11)
							)
						)
						((Said '/system,pedestal,bin')
							(if climbedOutOfReactorRoom
								(Print 13 12)
							else
								(Print 13 13)
							)
						)
						((Said '/banister,banister') (Print 13 14))
						((Said 'look/partition') (Print 13 15))
						((Said '[<at,around,in][/area,!*]')
							(if (or (== global132 4) (== global132 5))
								(Print 13 16)
							else
								(Print 13 17)
							)
						)
						((Said '/lamp<grabber,device,safety')
							(if (or (== global132 4) (== global132 5))
								(Print 13 18)
							else
								(Print 13 19)
							)
						)
						(
							(or
								(Said '[<down]/dirt,deck')
								(Said '<down[/dirt,deck]')
							)
							(Print 13 20)
						)
						(
							(or
								(Said '[<up,above]/ceiling')
								(Said '<up,above[/ceiling]')
							)
							(Print 13 21)
						)
					)
				)
				(if (Said 'holler,converse/android') (Print 13 22))
				(if (Said 'beat,attack/android') (Print 13 23))
				(if
					(or
						(Said 'climb[<up][/grabber,device]')
						(Said 'climb/banister,banister<up<to')
						(Said 'climb<up,on/banister,banister')
					)
					(Print 13 24)
				)
				(if (Said 'enter,(go<in,in)/system') (Print 13 25))
				(if
					(or
						(Said 'enter,board[/device,grabber,chair]')
						(Said 'get<on,in,onto,down/[device,grabber,chair]')
						(Said 'climb[<on,in,onto,in,down][/device,grabber,chair]')
					)
					(cond 
						((or (== global132 5) (== global132 4)) (Print 13 26))
						((ego inRect: grabberRect 110 (+ grabberRect 40) 116) (ego setScript: ontoGrabScript))
						((== global132 6) (ego setScript: grabScript))
						(else (Print 13 27))
					)
				)
				(if
					(or
						(Said
							'drag,press,use,manipulate,go[<forward,backward,back][/stick,throttle,control,knob]'
						)
						(Said
							'begin,manipulate,drive[<forward,backward,back]/grabber,device'
						)
					)
					(Print 13 28)
				)
				(if
					(Said
						'disembark,(stand[<up]),(climb,get<off,out)[/grabber,device,claw,!*]'
					)
					(if (or (== global132 5) (== global132 4))
						(cond 
							((ego mover?) (Print 13 29))
							((ego inRect: 126 103 154 107) (ego setScript: platformScript))
							(else (Print 13 30))
						)
					else
						(Print 13 31)
					)
				)
			)
			(direction
				(if
				(or isHandsOff (and (!= global132 4) (!= global132 5)))
					(return)
				)
				(switch (event message?)
					(dirN
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
					(dirS
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
					(dirNW
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
					(dirNE
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
					(dirSE
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
					(dirSW
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (!= local0 3)
			(if (== newRoomNumber 15) (theMusic fade:))
			(super newRoom: newRoomNumber)
		)
	)
)

(instance botPanelScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bot setLoop: 0 cel: 0 cycleSpeed: 4 setCycle: EndLoop self)
			)
			(1
				(bot cel: 0)
				(= seconds (Random 7 12))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance frontZapScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bot cycleSpeed: 0 setCycle: EndLoop self)
			)
			(1 (= cycles 15))
			(2
				(bot setLoop: 1 cel: 0 cycleSpeed: 0 setCycle: EndLoop self)
			)
			(3
				(bot
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 165 127 self
				)
			)
			(4
				(bot
					cel: 255
					loop:
						(cond 
							((== global132 6) 3)
							((< (ego x?) (- (bot x?) 20)) 1)
							((> (ego x?) (+ (bot x?) 45)) 2)
							(else 3)
						)
					setCycle: BegLoop self
				)
			)
			(5 (= seconds 3))
			(6
				(if
					(and
						(== local2 1)
						(or
							(ego inRect: 35 103 260 105)
							(and (== global132 6) (== (ego view?) 0))
						)
					)
					(bot
						cel: 0
						loop:
							(cond 
								((== global132 6) 7)
								((< (ego x?) (- (bot x?) 20)) 4)
								((> (ego x?) (+ (bot x?) 45)) 5)
								(else 7)
							)
						setCycle: EndLoop self
					)
					(zotSound play:)
				else
					(bot cel: 0 setCycle: EndLoop self)
				)
			)
			(7
				(if (> (bot loop?) 2)
					(if
						(and
							(== local0 0)
							(or (ego inRect: 35 103 260 105) (== global132 6))
						)
						(= local0 3)
						(HandsOff)
						(if (== global132 4)
							(grabber
								view: 752
								loop: (if (== motivatorState 3) 1 else 0)
								posn: (ego x?) (ego y?)
								setMotion: 0
								init:
							)
						)
						(ego hide:)
						(zot
							x: (if (!= global132 6) (+ (ego x?) 11) else (ego x?))
							y: (if (!= global132 6)
								(+ (ego y?) 45)
							else
								(- (ego y?) 6)
							)
							init:
						)
					)
					(= cycles 15)
					(ShakeScreen 5)
				else
					(= cycles 2)
				)
			)
			(8
				(if (cast contains: zot)
					(zot dispose:)
					(RedrawCast)
					(ego dispose:)
					(Print 13 32)
					(EgoDead 901 0 10 14)
				else
					(bot
						setLoop: 3
						setCycle: Walk
						setMotion: MoveTo 165 109 self
					)
				)
			)
			(9
				(bot setLoop: 1 cel: 255 setCycle: BegLoop self)
			)
			(10
				(= local2 0)
				(bot setScript: botPanelScript)
			)
		)
	)
)

(instance backZapScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bot cycleSpeed: 0 setCycle: EndLoop self)
			)
			(1 (= seconds 2))
			(2
				(bot setLoop: 9 cel: 0 cycleSpeed: 0 setCycle: EndLoop self)
			)
			(3
				(bot
					setLoop: 8
					setCycle: Walk
					setMotion: MoveTo 192 100 self
				)
			)
			(4 (= seconds 3))
			(5
				(if
				(and (== local2 1) (ego inRect: 143 74 237 78))
					(HandsOff)
					(bot cel: 0 setCycle: EndLoop self)
					(zotSound play:)
				else
					(bot setCycle: Walk setMotion: MoveTo 165 109 self)
				)
			)
			(6
				(if (< (bot y?) 105)
					(grabber
						view: 752
						posn: (ego x?) (ego y?)
						setLoop: (if (== motivatorState 3) 3 else 2)
						init:
						setPri: (ego priority?)
						setMotion: 0
					)
					(ego hide:)
					(zot posn: (+ (ego x?) 7) (+ (ego y?) 24) init:)
					(ShakeScreen 5)
					(= cycles 15)
				else
					(bot setLoop: 9 cel: 255 setCycle: BegLoop self)
				)
			)
			(7
				(if (cast contains: zot)
					(zot dispose:)
					(RedrawCast)
					(ego dispose:)
					(Print 13 32)
					(EgoDead 901 0 10 14)
				else
					(bot setLoop: 0 cel: 0 setScript: botPanelScript)
					(= local2 0)
				)
			)
		)
	)
)

(instance railWalkScript of Script
	(properties)
	
	(method (doit)
		(cond 
			(
			(and (== (ego view?) 0) (!= (ego onControl: 1) 16384)) (self changeState: 1) (HandsOff) (= programControl 1))
			((> (ego x?) 316) (ego setMotion: 0) (curRoom newRoom: 12))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setLoop: -1))
			(1
				(ego
					view: 17
					setLoop: (ego loop?)
					cel: 0
					x: (if (< (ego x?) 22) 22 else (ego x?))
					setMotion: 0
					illegalBits: 0
					setCycle: EndLoop self
				)
			)
			(2
				(ego
					setPri: (if (< (ego y?) 113) 8 else 9)
					setStep: 3 20
					setMotion: MoveTo (ego x?) 220 self
				)
				(theMusic number: 45 loop: 1 play:)
			)
			(3
				(= printObj (Print 13 33 #dispose))
				(= seconds 5)
			)
			(4 (cls) (EgoDead 901 0 0 1))
		)
	)
)

(instance ontoGrabScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= programControl TRUE)
				(HandsOff)
				(if (== global132 3)
					(ego
						view: 289
						posn: (+ grabberRect 7) (ego y?)
						cel: 0
						setMotion: 0
						cycleSpeed: 2
						setCycle: EndLoop self
					)
				else
					(self changeState: 2)
				)
			)
			(1 (= cycles 5))
			(2
				(grabber dispose:)
				(HandsOn)
				(ego setScript: grabScript)
				(= programControl FALSE)
			)
		)
	)
)

(instance grabScript of Script
	(properties)
	
	(method (doit)
		(if (not isHandsOff)
			(cond 
				((not (ego mover?))
					(cond 
						((and (== global132 4) (!= (ego loop?) 0)) (ego loop: 0))
						((and (== global132 5) (!= (ego loop?) 1)) (ego loop: 1))
					)
					(if (== (theMusic state?) 3) (theMusic stop:))
				)
				((== global132 4)
					(cond 
						(
							(and
								(== (ego loop?) 0)
								(or (!= (theMusic number?) 52) (!= (theMusic state?) 3))
							)
							(theMusic stop: number: 52 play:)
						)
						(
							(and
								(== (ego loop?) 1)
								(or (!= (theMusic number?) 53) (!= (theMusic state?) 3))
							)
							(theMusic stop: number: 53 play:)
						)
					)
				)
				((== global132 5)
					(cond 
						(
							(and
								(== (ego loop?) 1)
								(or (!= (theMusic number?) 52) (!= (theMusic state?) 3))
							)
							(theMusic stop: number: 52 play:)
						)
						(
							(and
								(== (ego loop?) 0)
								(or (!= (theMusic number?) 53) (!= (theMusic state?) 3))
							)
							(theMusic stop: number: 53 play:)
						)
					)
				)
			)
		)
		(if (not programControl)
			(if (== global132 4)
				(if (< (ego x?) -8)
					(= programControl TRUE)
					(HandsOff)
					(self changeState: 2)
				)
				(if (> (ego x?) 316)
					(ego setMotion: 0)
					(curRoom newRoom: 12)
				)
			)
			(if (== global132 5)
				(if (< (ego x?) 132)
					(= programControl TRUE)
					(HandsOff)
					(self changeState: 6)
				)
				(if (> (ego x?) 255)
					(ego setMotion: 0)
					(curRoom newRoom: 12)
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== global132 5)
					(ego
						view: (if (== motivatorState motivatorGRABBED) 261 else 23)
						setPri: 7
						posn: 242 76
						setStep: 2 1
						init:
						setMotion: MoveTo -40 76
					)
					(User prevDir: dirW)
				else
					(ego setStep: 3 1 setPri: 9 y: 104)
					(if (== global132 4)
						(ego x: 313 init: setMotion: MoveTo -40 (ego y?))
						(User prevDir: dirW)
					else
						(ego
							view: (if (== motivatorState motivatorGRABBED) 260 else 22)
							loop: 0
							cel: 0
						)
						(if (== global132 6)
							(ego x: (grabber x?))
							(grabber dispose:)
						else
							(ego x: grabberRect)
						)
						(= global132 4)
					)
				)
				(ego setCycle: Forward)
				(= cycles 2)
			)
			(1
				(if (< (ego x?) 230) (Print 13 34))
			)
			(2
				(HandsOff)
				(User canControl: FALSE canInput: TRUE)
				(ego
					view: 26
					setPri: 7
					setLoop: (if (!= motivatorState motivatorGRABBED) 2 else 5)
					illegalBits: 0
					posn: -9 102
					setStep: 1 2
					setMotion: MoveTo -9 96 self
				)
			)
			(3
				(ego
					setLoop: (if (!= motivatorState motivatorGRABBED) 1 else 4)
					posn: -6 94
					setStep: 5 1
					setMotion: MoveTo 59 81 self
				)
			)
			(4
				(ego
					view: (if (== motivatorState motivatorGRABBED) 261 else 23)
					setPri: -1
					setLoop: -1
					loop: 0
					setStep: 2 1
					posn: 132 76
					illegalBits: -32768
				)
				(= seconds 2)
			)
			(5
				(ego setMotion: MoveTo 321 76)
				(= programControl FALSE)
				(HandsOn)
				(= global132 5)
				(User prevDir: dirE)
			)
			(6
				(HandsOff)
				(User canControl: FALSE canInput: TRUE)
				(= seconds 2)
			)
			(7
				(ego
					view: 26
					setLoop: (if (!= motivatorState motivatorGRABBED) 0 else 3)
					setStep: 5 1
					posn: 59 81
					setMotion: MoveTo -6 94 self
				)
			)
			(8
				(ego
					setLoop: (if (!= motivatorState motivatorGRABBED) 2 else 5)
					posn: -9 96
					setStep: 1 2
					setMotion: MoveTo -9 102 self
				)
			)
			(9
				(ego
					view: (if (== motivatorState motivatorGRABBED) 260 else 22)
					setLoop: -1
					loop: 0
					setPri: 9
					setStep: 3 1
					illegalBits: -32768
					posn: -8 104
					setMotion: MoveTo 322 104
				)
				(HandsOn)
				(= global132 4)
				(= programControl FALSE)
				(User prevDir: dirE)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(super handleEvent: event)
		(if
			(or
				(!= (event type?) saidEvent)
				programControl
				(event claimed?)
			)
			(return)
		)
		(if
			(or
				(Said 'lower/claw[<grabber]')
				(Said 'use,press,press/claw,button')
			)
			(Print 13 35)
		)
	)
)

(instance platformScript of Script
	(properties)
	
	(method (doit)
		(if programControl (return))
		(super doit:)
		(if
			(or
				(== (ego onControl: 0) 5)
				(ego inRect: 99 168 190 172)
			)
			(self changeState: 1)
			(= programControl TRUE)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= grabberRect (ego x?))
				(grabber x: (ego x?) y: (ego y?) init:)
				(= grabberRect (ego x?))
				(ego
					view: 0
					loop: 2
					posn: 154 152
					setPri: 14
					cycleSpeed: 0
					setMotion: 0
					setCycle: Walk
					setStep: 3 2
				)
				(= global132 6)
			)
			(1
				(HandsOff)
				(ego
					view: 17
					setLoop: (ego loop?)
					cel: 0
					illegalBits: 0
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(2
				(if (!= local0 3)
					(= local0 1)
					(ego setCycle: 0)
					(if (ego inRect: 99 168 190 172)
						(ego setStep: 3 20 setMotion: MoveTo 148 229 self)
					else
						(ego
							setPri: 9
							setStep: 5 20
							setMotion: MoveTo (ego x?) 230 self
						)
						(self state: 4)
					)
					(theMusic number: 45 loop: 1 play:)
				)
			)
			(3
				(ego hide:)
				(Timer setCycle: self 2)
			)
			(4
				(if (not climbedOutOfReactorRoom)
					(theGame changeScore: 5)
				)
				(curRoom newRoom: 15)
			)
			(5
				(Print 13 36)
				(EgoDead 901 0 0 1)
			)
		)
	)
)

(instance grabber of Actor
	(properties
		view 752
	)
	
	(method (init)
		(super init:)
		(self setStep: 3 1 setPri: 9 ignoreActors: 1)
	)
)

(instance bot of Actor
	(properties
		view 10
	)
	
	(method (init)
		(super init:)
		(self
			cel: 0
			loop: 0
			posn: 165 109
			setPri: 8
			illegalBits: 0
			ignoreActors: 1
			setScript: botPanelScript
		)
	)
)

(instance zot of View
	(properties
		view 10
	)
	
	(method (init)
		(super init:)
		(self loop: 6 ignoreActors: TRUE setPri: 15)
	)
)

(instance zotSound of Sound
	(properties
		number 35
		priority 1
	)
)
