;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
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
	rm012 0
)

(local
	claw
	oldEgoX
	local2
	local3
	printObj
)
(instance rm012 of Room
	(properties
		picture 12
		style HSHUTTER
		horizon 10
		east 9
	)
	
	(method (init &tmp [temp0 50])
		(self setLocales: GRABBER)
		(User canInput: TRUE canControl: TRUE)
		(if (== global132 4)
			(Load VIEW 22)
			(Load VIEW 258)
			(Load VIEW 260)
		)
		(if (== global132 5)
			(Load VIEW 23)
			(Load VIEW 259)
			(Load VIEW 261)
		)
		(if (== global132 3)
			(Load VIEW 17)
			(Load SOUND 45)
			(Load SCRIPT JUMP)
			(= local3 991)
		else
			(Load SOUND 74)
			(Load SOUND 75)
			(Load SOUND 76)
		)
		(if (or (== global132 4) (== global132 5))
			(Load SOUND 52)
			(Load SOUND 53)
		)
		(ego setStep: -1 1)
		(theMusic priority: 0 loop: -1)
		(super init:)
		(self
			setScript: (if (== global132 3) railWalkScript else grabScript)
		)
	)
	
	(method (dispose)
		(if (== global132 3) (DisposeScript JUMP))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
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
				(if (Said 'look>')
					(cond 
						((Said '/door,corridor,partition,pit[<w]') (Print 12 0))
						((Said '[<at,around,in][/area,!*]') (if (== global132 3) (Print 12 1) else (Print 12 2)))
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
		(super newRoom: newRoomNumber)
	)
)

(instance railWalkScript of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(not programControl)
				(!= (ego onControl: origin) 16384)
			)
			(self changeState: 1)
			(User canControl: FALSE canInput: FALSE)
			(= programControl TRUE)
		)
		(if (< (ego x?) 4)
			(ego setMotion: 0)
			(curRoom newRoom: 13)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego init:)
				(if (== prevRoomNum 13) (ego posn: 10 123))
			)
			(1
				(ego
					view: 17
					setLoop: (ego loop?)
					cel: 0
					setMotion: 0
					illegalBits: 0
					setPri: (if (< (ego y?) 125) 8 else 9)
					setCycle: EndLoop self
				)
			)
			(2
				(ego setCycle: 0 setMotion: JumpTo (ego x?) 244 self)
				(theMusic number: 45 loop: 1 play:)
			)
			(3
				(ego hide:)
				(= printObj (Print 12 3 #dispose))
				(= seconds 5)
			)
			(4 (cls) (EgoDead 901 0 0 1))
		)
	)
)

(instance grabScript of Script
	(properties)
	
	(method (doit)
		(if isHandsOff (return))
		(cond 
			((and (< grabberState 2) (not (ego mover?)))
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
		(if (and (== global132 5) (< (ego x?) 95))
			(ego setMotion: 0)
			(curRoom newRoom: 13)
		)
		(if (and (== global132 4) (< (ego x?) 4))
			(ego setMotion: 0)
			(curRoom newRoom: 13)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== global132 5)
						(ego
							view: (if (== motivatorState motivatorGRABBED) 261 else 23)
							setPri: -1
							setStep: 2
							y: 88
						)
					)
					((== global132 4)
						(ego
							view: (if (== motivatorState motivatorGRABBED) 260 else 22)
							setPri: 9
							setStep: 3
							y: 116
						)
					)
				)
				(ego init: setCycle: Forward)
				(if (< grabberState 4)
					(ego
						setMotion: MoveTo (if (== prevRoomNum 13) 400 else -40) (ego y?)
					)
				)
				(if (== prevRoomNum 13)
					(User prevDir: dirE)
					(if (== global132 5)
						(ego x: (if (== global132 5) 98 else 4))
					else
						(ego x: 4)
					)
				)
				(if (== prevRoomNum 9) (User prevDir: dirW))
				(if (> grabberState 3)
					(ego
						view: (if (== global132 4) 22 else 23)
						loop: 2
						posn: grabberX grabberY
					)
					(User canControl: FALSE)
					(ego setScript: clawScript)
				)
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
			((Said 'look/grabber') (Print 12 4))
			(
				(or
					(Said 'lower/claw[<grabber]')
					(Said 'use,press,press/claw,button')
				)
				(cond 
					((ego mover?) (Print 12 5))
					(
						(or
							(< (ego x?) 30)
							(and (== global132 5) (< (ego x?) 124))
						)
						(Print 12 6)
					)
					((and (!= grabberState 2) (!= grabberState 3))
						(= inCartoon TRUE)
						(HandsOff)
						(ego setMotion: 0)
						(clunk number: 74 loop: 1 play:)
						(ego setScript: clawScript)
					)
					(else (Print 12 7))
				)
			)
			((Said '/chair[<grabber,device]') (Print 12 8))
			((Said 'disembark[/grabber,device,!*]') (Print 12 9))
		)
	)
)

(instance clawScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: 0)
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
						view: (if (== motivatorState 3)
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
					(if (> oldEgoX 212) (= gGGGNorth 1) else (= gGGGNorth 0))
					(= grabberX (ego x?))
					(= grabberY (ego y?))
					(if (== global132 4)
						(curRoom newRoom: 3)
					else
						(curRoom newRoom: 2)
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
