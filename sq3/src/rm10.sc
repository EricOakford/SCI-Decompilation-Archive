;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm10 0
)

(local
	notAPrettySight
	local1
	printObj
	local3
)
(instance grind of Sound
	(properties
		number 43
		priority 1
		loop -1
	)
)

(instance rm10 of Room
	(properties
		picture 10
		style HSHUTTER
		horizon 10
		east 11
		west 9
	)
	
	(method (init &tmp [temp0 50])
		(self setLocales: GRABBER)
		(User canInput: TRUE canControl: FALSE)
		(Load VIEW 750)
		(Load VIEW 20)
		(Load SOUND 42)
		(Load SOUND 43)
		(cond 
			((== global132 4) (Load VIEW 22) (Load VIEW 258) (Load VIEW 260))
			((== global132 5) (Load VIEW 23) (Load VIEW 259) (Load VIEW 261))
			(else
				(Load SCRIPT JUMP)
				(= local3 991)
				(Load VIEW 0)
				(Load VIEW 17)
				(Load VIEW 24)
			)
		)
		(if (and (!= prevRoomNum 9) (!= prevRoomNum 11))
			(= global132 2)
		)
		(cond 
			((== global132 3) (HandsOn) (ego init: setScript: railJump))
			((== global132 2)
				(ego
					view: 750
					posn: 2 145
					setStep: 3 1
					setLoop: 1
					setCel: 2
					setPri: 8
					init:
					setCycle: 0
				)
			)
			((or (== global132 5) (== global132 4)) (ego setScript: grabScript))
		)
		(if (or (> howFast 0) (== global132 2))
			(pile1 init:)
			(fallPile init:)
			(shredder init:)
			(theMusic number: 42 loop: -1 priority: 0 play:)
		else
			(shredder init: addToPic:)
		)
		(super init:)
		(self setScript: rmScript)
	)
	
	(method (dispose)
		(if (== global132 3) (DisposeScript 991))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (or (event claimed?) (> programControl TRUE))
			(return)
		)
		(switch (event type?)
			(mouseDown
				(if
				(and (not isHandsOff) (== (User controls?) TRUE))
					(ego setMotion: MoveTo (event x?) (ego y?))
					(event claimed: TRUE)
				)
			)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '/conveyer,belt')
								(if (and (== howFast 0) (!= global132 2))
									(Print 10 0)
								else
									(Print 10 1)
								)
							)
							((Said '/iron,debris,scrap,heap')
								(if (cast contains: shredder)
									(Print 10 2)
								else
									(Print 10 3)
								)
							)
							((Said '/tackle,post,device') (if (== howFast 0) (Print 10 4) else (Print 10 5)))
							((Said '/conduit') (Print 10 6))
							((Said '[<at,around,in][/area,!*]')
								(cond 
									((== global132 3) (Print 10 7))
									((== global132 2) (Print 10 8))
									(else (Print 10 9))
								)
							)
						)
					)
					(
						(Said
							'climb,descend,(climb[<up,down,on,onto])/conduit,sheet[<iron]'
						)
						(Print 10 10)
					)
					(
					(Said 'jump,leap,(get,climb<on,onto)[<off]/conveyer,belt') (if (== global132 2) (Print 10 11) else (Print 10 12)))
					((Said '(get<up),stand[<up][/belt,conveyer]')
						(if (== global132 2)
							(if (!= (ego view?) 0)
								(HandsOn)
								(ego
									view: 0
									setLoop: -1
									loop: 0
									setCycle: Walk
									setMotion: 0
									setStep: 3 1
								)
							else
								(Print 10 13)
							)
						else
							(event claimed: FALSE)
							(return)
						)
					)
					((Said 'get/banister') (if (== global132 2) (Print 10 14) else (Print 10 15)))
					(
					(Said 'jump,leap,climb[<onto,on,to,up,down]/conduit') (Print 10 16))
					(
					(and (== global132 2) (Said 'climb<up,onto/banister')) (Print 10 14))
					(
						(Said
							'jump,leap[<to,onto,on,up,down,off][/banister,banister]'
						)
						(if (== global132 2)
							(if (== (ego view?) 0)
								(ego setScript: railJump)
							else
								(Print 10 17)
							)
						else
							(event claimed: FALSE)
							(return)
						)
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
		(if (!= global132 2)
			(theMusic stop:)
			(super newRoom: newRoomNumber)
		)
	)
)

(instance newPileScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fallPile setCycle: EndLoop self)
			)
			(1
				(pile1
					setCel: (Random 0 1)
					posn: -32 145
					show:
					setMotion: MoveTo 175 145 self
				)
			)
			(2
				(pile1 hide:)
				(self changeState: 0)
			)
		)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (doit)
		(if (and (== global132 2) (> (ego x?) 174))
			(User canControl: FALSE canInput: FALSE)
		)
		(if
			(and
				(== programControl 0)
				(== (rmScript state?) 0)
				(== (ego view?) 0)
				(== global132 2)
			)
			(ego posn: (+ (ego x?) 3) (ego y?))
			(if (> (ego x?) 182)
				(rmScript changeState: 1)
				(return)
			)
			(if (or (< (ego y?) 141) (> (ego y?) 147))
				(ego setScript: faller)
				(return)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== global132 2)
					(ego setMotion: MoveTo 184 145 self)
				)
			)
			(1
				(HandsOff)
				(= programControl TRUE)
				(ego
					setStep: 5 5
					setPri: 9
					setCycle: 0
					setMotion: JumpTo 199 162 self
				)
			)
			(2
				(ego hide:)
				(grind play:)
				((= notAPrettySight (Prop new:))
					view: 20
					setLoop: 1
					posn: 203 176
					setPri: 15
					init:
					setCycle: Forward
					ignoreActors: TRUE
				)
				(= seconds 3)
			)
			(3
				(notAPrettySight dispose:)
				(grind fade:)
				(= cycles 5)
			)
			(4
				(= printObj (Print 10 18 #dispose))
				(= seconds 6)
			)
			(5 (cls) (EgoDead 901 0 3 4))
		)
	)
)

(instance railJump of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(not programControl)
				(!= (ego onControl: 0) 16384)
			)
			(ego setScript: faller)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== global132 3)
					(if (== prevRoomNum 9) (ego x: 4))
				else
					(HandsOff)
					(= programControl TRUE)
					(ego
						setMotion: 0
						view: 24
						setLoop: 0
						cel: 0
						cycleSpeed: 1
						illegalBits: -32768
						setCycle: EndLoop self
					)
				)
			)
			(1
				(ego setLoop: 1 setCel: 0 setPri: 9 posn: (ego x?) 144)
				(= seconds 2)
			)
			(2
				(ego cycleSpeed: 2 setCycle: EndLoop self)
			)
			(3
				(ego
					setLoop: 2
					setCel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(4
				(ego
					setLoop: 3
					setCel: 0
					posn: (ego x?) 122
					setCycle: EndLoop self
				)
			)
			(5
				(ego
					view: 0
					posn: (+ (ego x?) 9) 123
					setLoop: -1
					loop: 1
					setCycle: Walk
					cycleSpeed: 0
					setDirection: 0
				)
				(HandsOn)
				(if (not jumpedOntoRailing)
					(= jumpedOntoRailing TRUE)
					(theGame changeScore: 10)
				)
				(= global132 3)
				(= programControl FALSE)
			)
			(6 (= global132 3))
		)
	)
)

(instance faller of Script
	(properties)
	
	(method (doit)
		(if (and (< (ego loop?) 11) (> (ego y?) 147))
			(ego setPri: 11)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= programControl TRUE)
				(ego
					view: 17
					setLoop: (ego loop?)
					cel: 0
					illegalBits: 0
					setCycle: EndLoop self
					setMotion: 0
					cycleSpeed: 2
				)
				(if (== global132 2)
					(ego setPri: (if (< (ego y?) 141) 4 else 10))
				else
					(ego setPri: (if (< (ego y?) 125) 8 else 9))
				)
			)
			(1
				(ego
					setStep: 1 15
					setMotion: MoveTo (ego x?) 244 self
					setCycle: 0
				)
				(theMusic number: 45 loop: 1 priority: 15 play:)
			)
			(2
				(ego hide:)
				(= printObj
					(if (== global132 2)
						(Print 10 19 #dispose)
					else
						(Print 10 20 #dispose)
					)
				)
				(= seconds 5)
			)
			(3 (cls) (EgoDead 901 0 0 1))
		)
	)
)

(instance grabScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(if (== prevRoomNum 9) (ego x: 4))
				(if (== global132 5)
					(ego setStep: 2 setPri: 5)
				else
					(ego setPri: 14 setStep: 3)
				)
				(ego init: setCycle: Forward)
				(if (< grabberState 4)
					(ego
						setMotion: MoveTo (if (== prevRoomNum 9) 400 else -50) (ego y?)
					)
					(User prevDir: (if (== prevRoomNum 9) 3 else 7))
				)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/grabber,control') (Print 10 21))
			(
				(or
					(Said 'lower/claw[<grabber]')
					(Said 'use,press,press/claw,button')
				)
				(Print 10 22)
			)
			((Said '/chair[<grabber,device]') (Print 10 23))
			((Said 'disembark[/grabber,device,!*]') (Print 10 24))
		)
	)
)

(instance pile1 of Actor
	(properties
		view 750
	)
	
	(method (init)
		(super init:)
		(self
			setCel: 1
			setLoop: 0
			ignoreActors: TRUE
			illegalBits: 0
			posn: -32 145
			setPri: 6
			setStep: 3 1
		)
	)
)

(instance fallPile of Prop
	(properties
		view 750
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 2
			cel: 0
			ignoreActors: 1
			posn: 187 159
			setPri: 10
			setScript: newPileScript
		)
	)
)

(instance shredder of Prop
	(properties
		view 20
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors: 1
			posn: 204 169
			setPri: 9
			setCycle: Forward
		)
	)
)
