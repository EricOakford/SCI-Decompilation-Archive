;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include game.sh)
(use Main)
(use Intrface)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm003 0
)
(synonyms
	(craft craft)
)

(local
	local0
	grabber
	printObj
)
(instance rm003 of Room
	(properties
		picture 3
		style HSHUTTER
		horizon 52
		north 2
		east 4
	)
	
	(method (init &tmp [temp0 50])
		(= egoBlindSpot 120)
		(if (!= prevRoomNum 12)
			(self setLocales: JUNKBAY setFeatures: jupiter acme bow bore)
		)
		(if (== prevRoomNum 12) (HandsOff) else (HandsOn))
		(if (== prevRoomNum 12)
			(Load VIEW 258)
			(Load SOUND 76)
		else
			(Load VIEW 0)
			(Load VIEW 193)
			(Load VIEW 6)
		)
		(Load SOUND 45)
		(if (== roomWithMotivator curRoomNum)
			(Load VIEW 36)
			(motivator init:)
		)
		(if (or (== prevRoomNum 15) (== prevRoomNum 4))
			(ego setLoop: -1)
		)
		(if (or (== prevRoomNum 4) (== prevRoomNum 15))
			(Load SOUND 11)
			(theMusic number: 11 loop: -1 play:)
		)
		(super init:)
		(if (and (!= prevRoomNum 15) (!= prevRoomNum 4))
			(ego posn: 127 57 loop: 1)
		)
		(if (== prevRoomNum 15)
			(if (not climbedOutOfReactorRoom) (Print 3 0) (= climbedOutOfReactorRoom TRUE))
			(ego
				view: 6
				posn: 37 162
				setLoop: -1
				setPri: -1
				illegalBits: cWHITE
			)
		)
		(= programControl FALSE)
		(if (== prevRoomNum 12)
			(self setScript: grabScript)
		else
			(ego init:)
		)
	)
	
	(method (doit)
		(if (or (== (ego view?) 0) (== (ego view?) 6))
			(cond 
				(
					(or
						(== (ego onControl: 0) 4)
						(== (ego onControl: 0) 5)
					)
					(ego view: 6)
				)
				((== (ego onControl: 0) 1) (ego view: 0))
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (or programControl (event claimed?)) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '/bow,bow,tie') (Print 3 1))
							((Said '/acme') (Print 3 2))
							((Said '/jup,2') (Print 3 3))
							((Said '/nozzle') (Print 3 4))
							(
								(and
									climbedOutOfReactorRoom
									(InRoom iLadder 15)
									(Said 'look/ladder')
								)
								(if (ego inRect: 22 149 66 175)
									(Print 3 5)
								else
									(Print 3 6)
								)
							)
							(
							(and climbedOutOfReactorRoom (Said '[<in,in,through,at]/cavity'))
								(if (ego inRect: 22 149 66 175)
									(Print 3 7)
								else
									(Print 3 8)
								)
							)
							(
							(Said '[<in,through,at,in]/pane,port,(port<cavity)')
								(if (ego inRect: 39 148 105 167)
									(Print 3 9)
								else
									(NotClose)
								)
							)
							((Said '/box') (Print 3 10))
							((Said '[<below,at]/iron') (Print 3 11))
							((Said '[<around,at,in][/area,!*]') (Print 3 12))
						)
					)
					(
					(Said 'break,open/pane,glass,aperture,port,(cavity<port)') (Print 3 13))
					(
						(Said
							'hoist,manipulate,press,drag/craft,chute,bow,bow,tie,bore,acme,jup,2,(craft<flying)'
						)
						(Print 3 14)
					)
					((Said 'listen[/sound]') (Print 3 15))
					(
						(Said
							'open,board,enter,(climb[<in,in])/craft,bow,bow,tie,bore,acme,jup,2,(craft<flying),door'
						)
						(Print 3 16)
					)
					((Said 'walk,climb[<on,up]/craft') (Print 3 17))
					((Said 'clean') (Print 3 18))
					((Said 'get,manipulate/box') (Print 3 19))
					(
						(Said
							'erect,stand,place,drop,lower,use/ladder[/cavity[<in,in,in]]'
						)
						(if
						(or (ego has: iLadder) ((inventory at: iLadder) ownedBy: 8))
							(if (ego inRect: 22 149 66 175)
								(if (ego has: iLadder)
									(PutInRoom iLadder 15)
									(theGame changeScore: -10)
									(Print 3 20)
								else
									(Print 3 21)
								)
							else
								(Print 3 22)
							)
						else
							(event claimed: FALSE)
							(return)
						)
					)
					((Said 'descend,climb,go[<down][/ladder,cavity]')
						(if climbedOutOfReactorRoom
							(if (ego inRect: 22 149 66 175)
								(if
								(and (not (ego has: iLadder)) (not (InRoom iLadder 8)))
									(ego hide:)
									(RedrawCast)
									(curRoom newRoom: 15)
								else
									(Print 3 23)
								)
							else
								(Print 3 22)
							)
						else
							(Print 3 24)
						)
					)
					((Said 'use,get,climb/debris,scrap,iron')
						(if (ego inRect: 210 89 276 110)
							(ego setScript: bleedScript)
						else
							(Print 3 25)
						)
					)
					((Said 'get/ladder')
						(if climbedOutOfReactorRoom
							(if (and (not (ego has: iLadder)) (not global134))
								(if (ego inRect: 22 149 66 175)
									(theGame changeScore: 10)
									(ego get: iLadder)
									(Print 3 26)
									(Print 3 27)
								else
									(Print 3 22)
								)
							else
								(Print 3 28)
							)
						else
							(Print 3 29)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if
		(or (== newRoomNumber 15) (== newRoomNumber 4))
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
				(= programControl TRUE)
				(ego hide:)
				(Print 3 30)
				(Timer set: self 2)
				(fallTune play:)
			)
			(1
				(Print 3 31)
				(EgoDead 901 0 0 1)
			)
		)
	)
)

(instance grabScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= grabber (Actor new:))
					view: 258
					setLoop: (if (== motivatorState 3) 2 else 0)
					setPri: 8
					setStep: 1 2
					x: (if gGGGNorth 249 else 81)
					y: -16
					illegalBits: 0
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					init:
					setCycle: Forward
					setMotion: MoveTo (if gGGGNorth 249 else 81) 99 self
				)
			)
			(1 (Timer set: self 2))
			(2
				(cond 
					((== motivatorState motivatorGRABBED)
						(= grabberState 4)
						(= motivatorState gGGGNorth)
						(motivator init:)
						(= roomWithMotivator curRoomNum)
						(theMusic number: 76 loop: 1 play:)
						(theGame changeScore: -15)
						(RedrawCast)
						(Print 3 32)
					)
					(
						(and
							(== roomWithMotivator curRoomNum)
							(== motivatorState gGGGNorth)
						)
						(grabber setLoop: 2)
						(motivator hide:)
						(= roomWithMotivator 0)
						(= motivatorState motivatorGRABBED)
						(= grabberState 5)
						(theMusic number: 76 loop: 1 play:)
						(theGame changeScore: 15)
						(RedrawCast)
						(Print 3 33)
					)
					(else (Print 3 34) (= grabberState 4))
				)
				(self changeState: 3)
			)
			(3
				(grabber
					setLoop: (if (== motivatorState 3) 2 else 0)
					setMotion: MoveTo (if gGGGNorth 249 else 81) -16 self
				)
			)
			(4 (curRoom newRoom: 12))
		)
	)
)

(instance bleedScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 193
					illegalBits: 0
					setLoop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
				(= printObj (Print 3 35 #dispose))
			)
			(1
				(ego setLoop: 1 cel: 0 cycleSpeed: 1 setCycle: Forward)
				(= seconds 4)
				(cls)
			)
			(2
				(ego cycleSpeed: 3)
				(= cycles 25)
			)
			(3
				(ego cycleSpeed: 6)
				(= seconds 4)
			)
			(4
				(ego cycleSpeed: 3)
				(= cycles 25)
			)
			(5
				(ego cel: 0 setCycle: 0)
				(RedrawCast)
				(Print 3 36 #at -1 20 #width 280)
				(EgoDead 901 0 6 8)
			)
		)
	)
)

(instance motivator of Actor
	(properties
		view 36
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			setCel: 0
			x: (if (== motivatorState motivatorONFLOOR) 264 else 96)
			y: 115
			setPri: 7
			stopUpd:
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '/motivator,artifact,device') (if motivatorKnown (Print 3 37) else (Print 3 38)))
					(
						(or
							(Said '/plug[<modular,8,spaceware]')
							(Said '/spaceware[<8]')
						)
						(Print 3 39)
					)
					((Said '[<down,below,at][/dirt,deck]') (Print 3 40))
				)
			)
			(
				(Said
					'turn,get,manipulate,press,roll,drag/motivator,artifact,device'
				)
				(Print 3 41)
			)
			(
			(Said '(turn<on),begin/motivator,artifact,device') (Print 3 42))
			(
			(Said 'press,drag,remove,get/plug[<modular,8,spaceware]') (Print 3 43))
		)
	)
)

(instance jupiter of Feature
	(properties
		y 75
		x 269
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (event claimed?) (!= (event type?) saidEvent)) (return))
			((Said 'look/craft') (Print 3 3))
		)
	)
)

(instance bow of Feature
	(properties
		y 144
		x 61
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (event claimed?) (!= (event type?) saidEvent)) (return))
			((Said 'look/craft,bow,tie,bow') (Print 3 1))
		)
	)
)

(instance bore of Feature
	(properties
		y 138
		x 212
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (event claimed?) (!= (event type?) saidEvent)) (return))
			((Said 'look/craft,bore,implement') (Print 3 44))
		)
	)
)

(instance acme of Feature
	(properties
		y 85
		x 172
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (event claimed?) (!= (event type?) saidEvent)) (return))
			((Said 'look/craft') (Print 3 45))
		)
	)
)

(instance fallTune of Sound
	(properties
		number 45
		priority 5
	)
)
