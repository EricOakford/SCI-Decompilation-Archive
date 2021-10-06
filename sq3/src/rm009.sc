;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9)
(include game.sh)
(use Main)
(use Intrface)
(use Timer)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm009 0
)

(local
	bucketCel
	claw
	oldEgoX
	local3
	local4
	printObj
)
(instance rm009 of Room
	(properties
		picture 9
		style HSHUTTER
		horizon 10
		east 10
		west 12
	)
	
	(method (init &tmp [temp0 50])
		(self setLocales: GRABBER)
		(if (== prevRoomNum 4) (HandsOff) else (HandsOn))
		(Load VIEW 13)
		(Load VIEW 750)
		(Load VIEW 17)
		(Load SOUND 75)
		(Load SOUND 76)
		(Load SOUND 74)
		(Load SOUND 45)
		(cond 
			((== global132 4) (Load VIEW 22) (Load VIEW 258) (Load VIEW 260))
			((== global132 5) (Load VIEW 23) (Load VIEW 259) (Load VIEW 261))
			((== global132 1) (Load SOUND 56))
			(else (Load SCRIPT JUMP) (= local4 991))
		)
		(if (or (== global132 4) (== global132 5))
			(Load SOUND 52)
			(Load SOUND 53)
		else
			(Load SOUND 41)
		)
		(theMusic priority: 1)
		(ego setStep: -1 1)
		(super init:)
		(self setScript: rmScript)
	)
	
	(method (dispose)
		(if (== global132 3) (DisposeScript JUMP))
		(super dispose:)
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
							((Said '/conveyer,belt,bucket,hoist,elevator') (if (== howFast 0) (Print 9 0) else (Print 9 1)))
							((Said '/iron,scrap,debris,heap,garbage') (if (== howFast 0) (Print 9 2) else (Print 9 3)))
							((Said '[<at,around,in][/area,!*]')
								(cond 
									((== global132 3) (Print 9 4))
									((== global132 2) (Print 9 5))
									(else (Print 9 6))
								)
							)
						)
					)
					((Said 'jump/conveyer,belt,bucket') (Print 9 7))
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
		(if (== global132 2) (ego cycleSpeed: 0))
		(if (and (!= global132 4) (!= global132 5))
			(theMusic stop:)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or (> howFast 0) (== global132 1))
					(elevSound play:)
					(pile init:)
					(bucket init:)
				)
				(if (> global132 1)
					(HandsOn)
					(cond 
						((== global132 3) (ego init: setScript: railWalkScript))
						((or (== global132 5) (== global132 4)) (ego setScript: grabScript))
					)
				)
			)
		)
	)
)

(instance pileScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(pile setMotion: MoveTo 337 147)
			)
			(1
				(pile
					x: (if (< prevRoomNum 9) 271 else 309)
					y: 147
					setCel: bucketCel
				)
				(self changeState: 0)
			)
		)
	)
)

(instance bScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bucket setCycle: 0 setMotion: MoveTo 240 114 self)
			)
			(1
				(= bucketCel (bucket cel?))
				(if (== (bucket cel?) 2)
					(ego setCycle: 0 setScript: dumpScript init:)
				)
				(bucket
					setLoop: 1
					cel: 0
					posn: 256 114
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(2
				(bucket setLoop: 0 setCel: (Random 0 1) posn: 240 212)
				(self changeState: 0)
				(pileScript cue:)
			)
		)
	)
)

(instance dumpScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 17
					setLoop: 0
					cel: 0
					setPri: 6
					posn: 255 95
					illegalBits: 0
					ignoreActors: 1
					setStep: 4 8
					setMotion: MoveTo 289 159 self
					cycleSpeed: 1
					setCycle: 0
					setCycle: EndLoop
				)
				(= global132 2)
			)
			(1
				(thump play:)
				(ego
					view: 750
					setLoop: 1
					cel: 0
					posn: 291 147
					cycleSpeed: 2
					setCycle: EndLoop
					setStep: 2 1
					setMotion: MoveTo 400 147
				)
			)
		)
	)
)

(instance railWalkScript of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(not programControl)
				(!= (ego onControl: origin) cYELLOW)
			)
			(HandsOff)
			(RedrawCast)
			(self changeState: 1)
			(= programControl TRUE)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego
					view: 17
					setLoop: (ego loop?)
					cel: 0
					setPri: (if (< (ego y?) 125) 8 else 9)
					illegalBits: 0
					setCycle: EndLoop self
				)
			)
			(2
				(theMusic number: 45 loop: 1 priority: 15 play:)
				(ego
					setStep: 1 15
					setMotion: JumpTo (ego x?) 244 self
					setCycle: 0
				)
			)
			(3
				(ego hide:)
				(= printObj (Print 9 8 #dispose))
				(Timer set: self 5)
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
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== global132 5)
					(ego
						view: (if (== motivatorState 3) 261 else 23)
						setPri: 4
						setStep: 2
						y: 88
					)
				else
					(ego
						view: (if (== motivatorState 3) 260 else 22)
						setPri: 14
						setStep: 3
						y: 116
					)
				)
				(ego init: setCycle: Forward)
				(if (< grabberState 4)
					(ego
						setMotion: MoveTo (if (== prevRoomNum 10) -40 else 400) (ego y?)
					)
					(User prevDir: (if (== prevRoomNum 10) 7 else 3))
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
			((Said 'look/grabber') (Print 9 9))
			(
				(or
					(Said 'lower/claw[<grabber]')
					(Said 'use,press,press/claw,button')
				)
				(cond 
					((ego mover?) (Print 9 10))
					((and (!= grabberState 2) (!= grabberState 3))
						(HandsOff)
						(= inCartoon TRUE)
						(ego setMotion: 0)
						(clunk number: 74 loop: 1 play:)
						(ego setScript: clawScript)
					)
					(else (Print 9 11))
				)
			)
			((Said '/chair[<grabber,device]') (Print 9 12))
			((Said 'disembark[/grabber,device,!*]') (Print 9 13))
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
				(= local3
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
						y: (if (> grabberState 3) 191 else local3)
						init:
					)
				else
					(= oldEgoX (ego x?))
					(= local3
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
					setPri: (if (== global132 5) 4 else (ego priority?))
					ignoreActors: 1
					illegalBits: 0
					setCycle: Forward
					setMotion: MoveTo oldEgoX (if (> grabberState 3) local3 else 191) self
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
					(clunk
						number: (if (== grabberState 4) 75 else 76)
						play:
					)
					(HandsOn)
					(= inCartoon FALSE)
					(if (== grabberState 4)
						(= grabberState 0)
					else
						(= grabberState 1)
					)
					(clawScript dispose:)
				else
					(if (> oldEgoX 141) (= gGGGNorth 1) else (= gGGGNorth 0))
					(= grabberX (ego x?))
					(= grabberY (ego y?))
					(if (== global132 4)
						(curRoom newRoom: 4)
					else
						(curRoom newRoom: 5)
					)
				)
			)
		)
	)
)

(instance pile of Actor
	(properties
		view 750
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			setCel: (Random 0 1)
			setPri: 6
			ignoreActors: 1
			illegalBits: 0
			posn: 309 147
			setStep: 2
			setScript: pileScript
		)
	)
)

(instance bucket of Actor
	(properties
		view 13
		illegalBits $0000
		moveSpeed 1
	)
	
	(method (init)
		(super init:)
		(bucket
			posn: 240 (if (< prevRoomNum 9) 224 else 204)
			setPri: 7
			setStep: -1 2
			setLoop: 0
			setCel: (if (< global132 2) 2 else (Random 0 1))
			cycleSpeed: 4
			setScript: bScript
			ignoreActors: 1
		)
	)
)

(instance elevSound of Sound
	(properties
		number 41
		loop -1
	)
)

(instance thump of Sound
	(properties
		number 56
		priority 2
	)
)

(instance clunk of Sound
	(properties
		number 75
		priority 5
	)
)
