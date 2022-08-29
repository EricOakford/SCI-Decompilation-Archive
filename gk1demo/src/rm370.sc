;;; Sierra Script 1.0 - (do not remove this comment)
(script# 370)
(include game.sh) (include "370.shm")
(use Main)
(use Procs)
(use Print)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm370 0
)

(instance rm370 of Room
	(properties
		picture 370
		style FADEOUT
	)
	
	(method (init)
		(super init:)
		(theGame handsOff:)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(if (Btst fDayIsDone)
			(PalVary PALVARYSTART 370 0)
			(curRoom setScript: graceComeOut)
		else
			(theMusic1
				number: 3740
				setLoop: -1
				play:
				setVol: 0
				fade: 80 5 25 0
			)
			(curRoom setScript: startTheDay)
		)
	)
	
	(method (dispose)
		(theMusic1 fade:)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
)

(instance gabComeHome of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: 128 130
					view: 370
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego posn: 86 137 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2
				(bike init:)
				(ego posn: 58 127 loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(4
				(= seconds 3)
			)
			(5
				(if (Btst fDayIsDone)
					(Prints {The sky fades to dark here})
					(ego setScript: graceComeOut)
					(self dispose:)
				else
					(curRoom newRoom: 210)
				)
			)
		)
	)
)

(instance graceComeOut of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(bike init:)
				(= cycles 1)
			)
			(1
				(grace init: loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2
				(grace loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(grace
					posn: 46 130
					loop: 3
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 18 167 self
				)
			)
			(4
				(PalVary PALVARYREVERSE 6 0 1)
				(= seconds 7)
			)
			(5
				(if isDemo
					(Print addText: N_ROOM NULL C_END_DEMO 0 init:)
					(= cycles 5)
				else
					(Prints {The day is done})
					(Bclr fDayIsDone)
					(++ currentDay)
					(curRoom init:)
					(self dispose:)
				)
			)
			(6
				(= quit TRUE)
				(self dispose:)
			)
		)
	)
)

(instance startTheDay of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(bike init:)
				(= cycles 1)
			)
			(1
				(Bclr fUsingMagnifyingGlass)
				(theGame setCursor: HAND_CURSOR TRUE)
				(theSound1 number: 375 loop: 1 play:)
				(PalVary PALVARYSTART 370 6)
				(= seconds 7)
			)
			(2
				(day init: setCycle: EndLoop self)
			)
			(3
				(one init: setCycle: EndLoop self)
			)
			(4
				(= seconds 3)
			)
			(5
				(switch currentDay
					(1
						(messager say: N_ROOM NULL C_POEM_1 0 self)
					)
					(2
						(bike init:)
						(messager say: N_ROOM NULL C_POEM_2 0 self)
					)
					(3
						(messager say: N_ROOM NULL C_POEM_3 0 self)
					)
					(4
						(messager say: N_ROOM NULL C_POEM_4 0 self)
					)
					(5
						(messager say: N_ROOM NULL C_POEM_5 0 self)
					)
					(6
						(messager say: N_ROOM NULL C_POEM_6 0 self)
					)
					(7
						(messager say: N_ROOM NULL C_POEM_7 0 self)
					)
				)
			)
			(6
				(day addToPic:)
				(one addToPic:)
				(= cycles 3)
			)
			(7
				(curRoom drawPic: 370 PIXELDISSOLVE)
				(if (theGame keepBar?)
					(theIconBar draw:)
				)
				(= cycles 5)
			)
			(8
				(theSound1 number: 371 play:)
				(kidOnBike
					init:
					cycleSpeed: 11
					setPri: 13
					setCycle: CycleTo 7 1 self
				)
			)
			(9
				(theSound1 fade:)
				(theSound2 number: 372 play:)
				(kidOnBike loop: 0 cel: 8 setCycle: EndLoop self)
			)
			(10
				(paper init:)
				(kidOnBike dispose:)
				(grace
					init:
					view: 372
					setLoop: 1
					cel: 0
					posn: 18 167
					setCycle: Walk
					ignoreControl: -32768
					setMotion: MoveTo 48 129 self
				)
			)
			(11
				(grace posn: 39 125 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(12
				(paper dispose:)
				(grace loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(13
				(theSound1 number: 373 play:)
				(grace loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(14
				(theSound2 number: 374 play:)
				(PalVary PALVARYREVERSE 0)
				(PalVary PALVARYKILL)
				(curRoom newRoom: 210)
			)
		)
	)
)

(instance grace of Actor
	(properties
		x 39
		y 125
		view 371
	)
)

(instance kidOnBike of Prop
	(properties
		x 68
		y 116
		view 372
	)
)

(instance day of Prop
	(properties
		x 76
		y 68
		view 375
	)
	
	(method (init)
		(self setPri: 13)
		(super init:)
	)
)

(instance one of Prop
	(properties
		x 243
		y 46
		view 375
		loop 1
	)
	
	(method (init)
		(self setPri: 13)
		(super init:)
	)
)

(instance bike of View
	(properties
		x 86
		y 137
		view 370
		loop 2
	)
)

(instance paper of View
	(properties
		x 38
		y 127
		view 372
		loop 5
	)
)
