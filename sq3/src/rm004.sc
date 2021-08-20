;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
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
	rm004 0
)
(synonyms
	(craft craft)
)

(local
	claw
)
(instance rm004 of Room
	(properties
		picture 4
		style HSHUTTER
		west 3
	)
	
	(method (init &tmp [temp0 50])
		(if (!= prevRoomNum 9)
			(self setLocales: JUNKBAY)
		else
			(HandsOff)
		)
		(StatusLine enable:)
		(Load VIEW 13)
		(if (== roomWithMotivator curRoomNum)
			(Load VIEW 36)
			(motivator init:)
		)
		(if (== prevRoomNum 9)
			(Load VIEW 258)
			(Load SOUND 76)
		else
			(Load VIEW 0)
			(Load VIEW 6)
			(ego init:)
		)
		(Load SOUND 41)
		(theMusic number: 41 loop: -1 priority: 1 play:)
		(bucket init:)
		(super init:)
		(if (== prevRoomNum 9)
			(ego setScript: grabScript)
		else
			(HandsOn)
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
		(if
			(and
				(cast contains: ego)
				(== (ego onControl: 0) 3)
				(> (bucket y?) 89)
				(< (bucket y?) 105)
			)
			(HandsOff)
			(ego dispose:)
			(bucket setCel: 2)
			(if (not enteredConveyorBucket)
				(= enteredConveyorBucket TRUE)
				(theGame changeScore: 5)
			)
			(= global132 1)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							(
							(Said '/bucket,conveyer,device,elevator,hoist[/belt]') (Print 4 0))
							((Said '[<around,at,in][/area,!*]') (Print 4 1))
							(
								(Said
									'[<at]/appendage,finger,appendage,branch[<android,big]'
								)
								(Print 4 2)
							)
							((Said '/jup,craft,2,chute') (Print 4 3))
							(
							(Said '[<in,in,through,at]/craft,pane,port,jup,2,chute') (Print 4 4))
							((Said '/nozzle') (Print 4 5))
							((Said '/cog,wheel') (Print 4 6))
							((Said '/box,pulley,dome,pole') (Print 4 7))
							((Said '/pit') (Print 4 8))
						)
					)
					(
						(Said
							'cease,(turn<off),(close<down)/conveyer,elevator,hoist'
						)
						(Print 4 9)
					)
					(
						(Said
							'break,open/pane,glass,aperture,port,(cavity<port),finger,appendage,appendage'
						)
						(Print 4 10)
					)
					(
						(Said
							'board,enter,(get<on,onto,in,in),(climb,stair<in,up,in,onto,on)/bucket,conveyer,tower'
						)
						(Print 4 11)
					)
					((Said 'climb[<on,up]/craft,chute,jup,2') (Print 4 12))
					(
					(Said 'hoist,manipulate,press,drag/craft,chute,jup,2') (Print 4 13))
					(
						(Said
							'bite,tickle,buff/appendage,appendage[<android,big,green]'
						)
						(Print 4 14)
					)
					(
						(Said
							'get,manipulate,drag,press,hoist[<up]/box,pulley,dome,pole'
						)
						(Print 4 15)
					)
					(
						(Said
							'get,manipulate,drag,press,hoist,get,climb/appendage,appendage,finger[<android,big]'
						)
						(Print 4 16)
					)
					((Said 'get>')
						(if (Said '/cog,wheel')
							(Print 4 17)
						else
							(event claimed: FALSE)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (!= newRoomNumber 9) (theMusic fade:))
		(super newRoom: newRoomNumber)
	)
)

(instance bucket of Actor
	(properties)
	
	(method (init)
		(super init:)
		(bucket
			view: 13
			setPri: 6
			setCel: (Random 0 1)
			setLoop: 0
			illegalBits: 0
			ignoreActors: 1
			ignoreHorizon: 1
			posn: 242 65
			setStep: -1 2
			moveSpeed: 1
			setCycle: 0
			setMotion: MoveTo 242 -3 self
			setScript: bScript
		)
	)
)

(instance bScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (bucket setPri: 6))
			(1
				(if (== (bucket cel?) 2)
					(curRoom newRoom: 9)
				else
					(bucket
						setCel: (Random 0 1)
						posn: 242 107
						setMotion: MoveTo 242 -3 self
					)
					(self state: (- state 1))
				)
			)
		)
	)
)

(instance motivator of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 36
			setLoop: 0
			setCel: 0
			x: (if (== motivatorState motivatorONFLOOR) 227 else 102)
			y: 128
			setPri: 9
			stopUpd:
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '/motivator,artifact,device') (if motivatorKnown (Print 4 18) else (Print 4 19)))
					(
						(or
							(Said '/plug[<modular,8,spaceware]')
							(Said '/spaceware[<8]')
						)
						(Print 4 20)
					)
					((Said '[<down,below,at][/dirt,deck]') (Print 4 21))
				)
			)
			(
			(Said 'drag,press,remove,get/plug[<modular,8,spaceware]') (Print 4 22))
			(
				(Said
					'turn,get,manipulate,press,roll,drag/motivator,artifact,device'
				)
				(Print 4 23)
			)
			(
			(Said '(turn<on),begin/motivator,artifact,device') (Print 4 24))
		)
	)
)

(instance grabScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= claw (Actor new:))
					name: {Claw}
					view: 258
					setLoop: (if (== motivatorState 3) 2 else 0)
					setPri: 9
					setStep: 1 2
					x: (if gGGGNorth 211 else 88)
					y: -19
					illegalBits: 0
					ignoreHorizon: 1
					ignoreActors: 1
					init:
					setCycle: Forward
					setMotion: MoveTo (if gGGGNorth 211 else 88) 112 self
				)
			)
			(1 (Timer set: self 2))
			(2
				(cond 
					((== motivatorState motivatorGRABBED)
						(= grabberState 4)
						(= motivatorState gGGGNorth)
						(clunkTune play:)
						(theGame changeScore: -15)
						(motivator init:)
						(RedrawCast)
						(= roomWithMotivator curRoomNum)
						(Print 4 25)
					)
					(
						(and
							(== roomWithMotivator curRoomNum)
							(== motivatorState gGGGNorth)
						)
						(claw setLoop: 2)
						(motivator hide:)
						(= roomWithMotivator 0)
						(clunkTune play:)
						(theGame changeScore: 15)
						(= motivatorState motivatorGRABBED)
						(= grabberState 5)
						(RedrawCast)
						(Print 4 26)
					)
					(else (Print 4 27) (= grabberState 4))
				)
				(self changeState: 3)
			)
			(3
				(claw
					setLoop: (if (== motivatorState 3) 2 else 0)
					setMotion: MoveTo (claw x?) -19 self
				)
			)
			(4 (curRoom newRoom: 9))
		)
	)
)

(instance clunkTune of Sound
	(properties
		number 76
		priority 15
	)
)
