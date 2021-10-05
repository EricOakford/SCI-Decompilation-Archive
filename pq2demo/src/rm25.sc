;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm25 0
)

(local
	egoCarDoor
	hood
	pCar
	[local3 3]
	motelDoor1
	local7
	motelDoor2
	smoke
	swatGuy1
	swatGuy2
	pCarDoor
	[hoodPosn 10]
)
(instance keithAct of Actor)

(instance gasBomb of Actor)

(instance rm25 of Room
	(properties
		picture 25
		style HSHUTTER
	)
	
	(method (init)
		(super init:)
		((= hood (Prop new:))
			view: 51
			loop: 5
			cel: 2
			posn: [hoodPosn 0] [hoodPosn 1]
			setPri: 14
			init:
		)
		(Load VIEW 54)
		(Load VIEW 51)
		(Load VIEW 251)
		(Load VIEW 30)
		(Load VIEW 268)
		(Load VIEW 50)
		(Load VIEW 53)
		((= egoCarDoor (Actor new:))
			view: 54
			setLoop: 1
			setCel: 1
			posn: 160 180
			init:
			addToPic:
		)
		((= motelDoor1 (Prop new:))
			view: 251
			setLoop: 1
			setCel: 0
			setPri: 9
			posn: 261 155
			init:
			ignoreActors:
			addToPic:
		)
		((= motelDoor2 (Prop new:))
			view: 251
			setLoop: 2
			setCel: 0
			setCycle: 0
			setPri: 7
			posn: 104 72
			init:
			addToPic:
		)
		((View new:)
			view: 251
			setLoop: 0
			setCel: 2
			setPri: 10
			posn: 167 138
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 251
			setLoop: 0
			setCel: 0
			setPri: 0
			posn: 67 7
			init:
			ignoreActors: FALSE
			addToPic:
		)
		((View new:)
			view: 251
			loop: 6
			cel: 0
			setPri: 3
			posn: 104 110
			ignoreActors:
			init:
			addToPic:
		)
		(ego
			view: 6
			loop: 1
			cel: 1
			posn: 281 172
			priority: 13
			init:
			stopUpd:
		)
		((= keith keithAct)
			view: 53
			loop: 0
			cel: 6
			posn: 120 135
			priority: 9
			init:
			stopUpd:
		)
		(self setScript: swatArrives)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance pLight of Prop
	(method (doit)
		(self posn: (+ (pCar x?) 4) (- (pCar y?) 29))
		(super doit:)
	)
)


(instance swatArrives of Script
	;EO: Decompilation complete!
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= pCar (Actor new:))
					view: 54
					setLoop: 1
					setCel: 3
					posn: 64 156
					setPri: 11
					ignoreControl: -1
					init:
				)
				(pLight
					view: 54
					setLoop: 3
					setCel: 0
					setPri: 11
					setCycle: Forward
					ignoreActors:
					doit:
					init:
				)
				((= swatGuy1 (Prop new:))
					view: 268
					loop: 0
					cel: 2
					posn: 48 144
					setPri: 10
					ignoreActors: 0
					init:
					hide:
				)
				((= swatGuy2 (Actor new:))
					view: 30
					posn: 73 164
					setPri: 12
					setCycle: Walk
					init:
					hide:
				)
				((= pCarDoor (Prop new:))
					view: 51
					setLoop: 8
					setCel: 255
					posn: 82 151
					setPri: 12
					ignoreActors: 0
					init:
					hide:
				)
				(if (Btst 14)
					(pCar stopUpd:)
					(pCarDoor stopUpd: show:)
					(swatGuy1 stopUpd: show:)
					(swatGuy2 stopUpd: show:)
					(client setScript: 0)
					(return)
				)
				(pCar posn: -60 157)
				(self cue:)
			)
			(1
				(pCar posn: -60 157 setMotion: MoveTo 64 157 self)
			)
			(2
				(pCarDoor show: setCel: 0 setCycle: EndLoop self)
			)
			(3
				(pCarDoor stopUpd:)
				(swatGuy2 show:)
				(swatGuy1 show:)
				(= cycles 1)
			)
			(4
				(swatGuy1 stopUpd:)
				(pCar stopUpd:)
				(= seconds 3)
			)
			(5
				(swatGuy2
					view: 268
					setLoop: 2
					posn: 73 164
					cycleSpeed: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(swatGuy2
					view: 268
					setLoop: 3
					cycleSpeed: 1
					setCel: 0
					setCycle: EndLoop
				)
				(gasBomb
					view: 268
					setLoop: 4
					setPri: 14
					setStep: 10 10
					setCycle: Forward
					ignoreActors:
					ignoreControl: -1
					posn: 75 140
					setMotion: MoveTo 225 111 self
					init:
				)
			)
			(7
				(gasBomb dispose:)
				(swatGuy2
					view: 30
					posn: 73 164
					setLoop: 0
					setCel: 0
					cycleSpeed: 0
				)
				((View new:)
					view: 251
					setLoop: 0
					setCel: 1
					setPri: 9
					posn: 229 121
					init:
					stopUpd:
					addToPic:
				)
				((= smoke (Prop new:))
					view: 251
					setLoop: 8
					setCel: 0
					posn: 229 115
					setPri: 10
					init:
					setCycle: Forward
				)
				(= seconds 3)
			)
			(8
				(curRoom newRoom: 22)
			)
		)
	)
)