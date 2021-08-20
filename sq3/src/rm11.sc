;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm11 0
)

(local
	claw
	oldEgoX
	local2
	slippedFromGreasyRailing
	local4
	local5
)
(instance rm11 of Room
	(properties
		picture 11
		style $0000
		horizon 10
		west 10
	)
	
	(method (init &tmp [temp0 50])
		(self setLocales: GRABBER)
		(HandsOn)
		(Load VIEW 25)
		(Load SOUND 75)
		(Load SOUND 76)
		(Load SOUND 74)
		(if (== global132 3)
			(Load VIEW 17)
			(Load SOUND 45)
			(Load SCRIPT JUMP)
			(= local4 991)
		else
			(Load VIEW 22)
			(Load VIEW 258)
			(Load VIEW 260)
			(Load VIEW 23)
			(Load VIEW 259)
			(Load VIEW 261)
			(Load SOUND 52)
			(Load SOUND 53)
		)
		(super init:)
		(if (== global132 3)
			(ego setScript: railWalkScript)
		else
			(ego setScript: grabScript)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
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
							((Said '[<at,around,in][/area,!*]') (if (== global132 3) (Print 11 0) else (Print 11 1)))
							((Said '/device,artifact') (Print 11 2))
						)
					)
					((Said 'climb,jump/device') (Print 11 3))
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
		(super newRoom: newRoomNumber)
	)
)

(instance railWalkScript of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(not programControl)
				(!= (ego onControl: 0) 16384)
			)
			(self changeState: 1)
			(HandsOff)
			(= programControl TRUE)
			(if (> (ego x?) 240) (= slippedFromGreasyRailing TRUE))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setPri: 9 init:))
			(1
				(ego
					view: 17
					setLoop: (ego loop?)
					cel: 0
					setPri: (if (and (not slippedFromGreasyRailing) (< (ego y?) 125)) 8 else 9)
					setMotion: 0
					illegalBits: 0
					setCycle: EndLoop self
				)
			)
			(2
				(ego
					setStep: 1 15
					setMotion: MoveTo (ego x?) 244 self
					setCycle: 0
				)
				(theMusic number: 45 loop: 1 play:)
			)
			(3
				(ego hide:)
				(= local5
					(if slippedFromGreasyRailing
						(Print 11 4 #dispose)
					else
						(Print 11 5 #dispose)
					)
				)
				(= seconds 5)
			)
			(4 (cls) (EgoDead 901 0 0 1))
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
			(if (and (== global132 4) (> (ego x?) 248))
				(= programControl TRUE)
				(HandsOff)
				(self changeState: 1)
			)
			(if (and (== global132 5) (> (ego x?) 103))
				(= programControl TRUE)
				(HandsOff)
				(self changeState: 4)
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
						setPri: -1
						setStep: 2
						y: 88
					)
				else
					(ego
						view: (if (== motivatorState motivatorGRABBED) 260 else 22)
						setPri: 9
						setStep: 3
						y: 116
					)
				)
				(ego init: setCycle: Forward)
				(if (< grabberState 4)
					(ego setMotion: MoveTo 400 (ego y?))
					(User prevDir: 3)
				)
				(if (> grabberState 3)
					(ego
						view: (if (== global132 4) 22 else 23)
						loop: 2
						posn: grabberX grabberY
					)
					(HandsOff)
					(curRoom setScript: clawScript)
				)
			)
			(1
				(User canControl: FALSE canInput: TRUE)
				(HandsOff)
				(ego
					view: 25
					setLoop: (if (!= motivatorState motivatorGRABBED) 2 else 5)
					illegalBits: 0
					x: 251
					y: 114
					setPri: -1
					setStep: 1 2
					setMotion: MoveTo 251 108 self
				)
			)
			(2
				(ego
					setLoop: (if (!= motivatorState motivatorGRABBED) 0 else 3)
					setStep: 6 2
					setMotion: MoveTo 179 84 self
				)
			)
			(3
				(ego
					view: (if (== motivatorState motivatorGRABBED) 261 else 23)
					setLoop: -1
					loop: 1
					setStep: 2 1
					posn: 103 88
					illegalBits: -32768
					setMotion: MoveTo -50 88
				)
				(HandsOn)
				(User prevDir: 7)
				(= programControl FALSE)
				(= global132 5)
			)
			(4
				(User canControl: FALSE canInput: TRUE)
				(HandsOff)
				(ego
					view: 25
					setLoop: (if (!= motivatorState motivatorGRABBED) 1 else 4)
					setStep: 6 2
					posn: 179 84
					setMotion: MoveTo 251 108 self
				)
			)
			(5
				(ego
					setLoop: (if (!= motivatorState motivatorGRABBED) 2 else 5)
					setStep: 1 2
					setMotion: MoveTo 251 114 self
				)
			)
			(6
				(ego
					view: (if (== motivatorState motivatorGRABBED) 260 else 22)
					setLoop: -1
					loop: 0
					setPri: 9
					setStep: 3 1
					posn: 248 116
					setMotion: MoveTo -1 116
					illegalBits: cWHITE
				)
				(HandsOn)
				(User prevDir: dirW)
				(= global132 4)
				(= programControl FALSE)
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
		(cond 
			((Said 'look/grabber') (Print 11 6))
			(
				(or
					(Said 'lower/claw[<grabber]')
					(Said 'use,press,press/claw,button')
				)
				(cond 
					((ego mover?) (Print 11 7))
					((and (!= grabberState 2) (!= grabberState 3))
						(= inCartoon 1)
						(HandsOff)
						(ego setMotion: 0)
						(clunk number: 74 loop: 1 play:)
						(ego setScript: clawScript)
					)
					(else (Print 11 8))
				)
			)
			((Said '/chair[<grabber,device]') (Print 11 9))
			((Said 'disembark[/grabber,device,!*]') (Print 11 10))
		)
	)
)

(instance clawScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= oldEgoX (ego x?))
				(= local2
					(if (== global132 4)
						(+ (ego y?) 42)
					else
						(+ (ego y?) 22)
					)
				)
				(self changeState: 1)
			)
			(1
				(ego view: (if (== global132 4) 22 else 23) loop: 2)
				(if (not (cast contains: claw))
					((= claw (Actor new:))
						name: {Claw}
						x: oldEgoX
						y: (if (> grabberState 3) 191 else local2)
						init:
					)
				else
					(= oldEgoX (ego x?))
					(= local2
						(if (== global132 4)
							(+ (ego y?) 42)
						else
							(+ (ego y?) 22)
						)
					)
				)
				(claw
					view: (if (== global132 4) 258 else 259)
					setLoop: (if (== motivatorState 3) 2 else 0)
					setStep: 1 (if (== global132 4) 2 else 1)
					setPri: (ego priority?)
					ignoreActors: 1
					illegalBits: 0
					setCycle: Forward
					setMotion: MoveTo oldEgoX (if (> grabberState 3) local2 else 191) self
				)
			)
			(2
				(if (> grabberState 3)
					(ego
						view: (if (== motivatorState motivatorGRABBED)
							(+ (ego view?) 238)
						else
							(ego view?)
						)
						loop: 0
					)
					(claw dispose:)
					(HandsOn)
					(= inCartoon 0)
					(clunk
						number: (if (== grabberState 4) 75 else 76)
						play:
					)
					(if (== grabberState 4)
						(= grabberState 0)
					else
						(= grabberState 1)
					)
					(clawScript dispose:)
				else
					(if
						(or
							(and (== global132 4) (< oldEgoX 152))
							(and (== global132 5) (< oldEgoX 50))
						)
						(= gGGGNorth 0)
					else
						(= gGGGNorth 1)
					)
					(= grabberX (ego x?))
					(= grabberY (ego y?))
					(if (== global132 4)
						(curRoom newRoom: 8)
					else
						(curRoom newRoom: 7)
					)
				)
			)
		)
	)
)

(instance clunk of Sound
	(properties
		number 75
		priority 5
	)
)
