;;; Sierra Script 1.0 - (do not remove this comment)
(script# 5)
(include game.sh)
(use Main)
(use Intrface)
(use Timer)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm5 0
)

(local
	claw
)
(instance rm5 of Room
	(properties
		picture 5
		style HSHUTTER
		horizon 10
		west 2
	)
	
	(method (init &tmp [temp0 50])
		(if (!= prevRoomNum 9) (self setLocales: JUNKBAY))
		(StatusLine enable:)
		(if (== prevRoomNum 9) (HandsOff))
		(if (== roomWithMotivator curRoomNum)
			(Load VIEW 36)
			(motivator init:)
		)
		(if (== prevRoomNum 9)
			(Load SOUND 76)
			(Load VIEW 258)
		else
			(Load VIEW 0)
			(ego init:)
		)
		(super init:)
		(self setScript: rmScript)
	)
	
	(method (doit)
		(if
		(and (ego inRect: 152 138 292 148) (>= (ego x?) 289))
			(curRoom newRoom: 6)
		)
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
	
	(method (handleEvent event &tmp [temp0 50])
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							(
								(or
									(Said '<in,in,through/craft,stage[<eva]')
									(Said '[<in,in,through,at]/pit,tube')
								)
								(Print 5 0)
							)
							((Said '[<at]/craft,stage') (Print 5 1))
							(
								(or
									(Said '[<at]/bone,android,protrusion[<metallic]')
									(Said
										'[<at]/appendage,head[<bone,android,protrusion[<metallic]]'
									)
								)
								(Print 5 2)
							)
							(
							(Said '/mog,finger,head,appendage[<android,man]') (Print 5 3))
							((Said '[<around,at,in][/area,!*]') (Print 5 4))
						)
					)
					((Said 'converse/man,mog,android') (Print 5 5))
					((Said 'climb,crawl/craft,stage') (Print 5 6))
					((Said 'crawl/console') (Print 5 7))
					((Said 'get/toaster') (Print 5 8))
					(
						(Said
							'get,explore,climb[<on,onto,up]/bone,android,protrusion[<metallic]'
						)
						(Print 5 9)
					)
				)
			)
			(mouseDown
				(if
					(and
						(not (event claimed?))
						(ego inRect: 0 137 310 145)
						(> (event x?) 174)
						(< (event y?) 149)
					)
					(ego setMotion: MoveTo (event x?) (ego y?))
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					(global157 (ego setMotion: MoveTo 292 144))
					((== prevRoomNum 9) (motivator setScript: grabScript))
					((== prevRoomNum 6) (ego setStep: -1 2 posn: 288 142 init:))
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
			x: (if (== motivatorState motivatorONFLOOR) 147 else 46)
			y: 127 species 9
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
					((Said '/motivator,artifact,device') (if motivatorKnown (Print 5 10) else (Print 5 11)))
					(
						(or
							(Said '/plug[<modular,8,spaceware]')
							(Said '/spaceware[<8]')
						)
						(Print 5 12)
					)
					((Said '[<down,below,at][/dirt,deck]') (Print 5 13))
				)
			)
			(
				(Said
					'turn,get,manipulate,press,roll,drag/motivator,artifact,device'
				)
				(Print 5 14)
			)
			(
			(Said '(turn<on),begin/motivator,artifact,device') (Print 5 15))
			(
			(Said 'press,drag,remove,get/plug[<modular,8,spaceware]') (Print 5 16))
		)
	)
)

(instance grabScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= claw (Actor new:))
					view: 258
					setLoop: (if (== motivatorState motivatorGRABBED) 2 else 0)
					setPri: 9
					setStep: 1 2
					x: (if gGGGNorth 134 else 30)
					y: -19
					illegalBits: 0
					ignoreHorizon: 1
					ignoreActors: 1
					init:
					setCycle: Forward
					setMotion: MoveTo (if gGGGNorth 134 else 30) 110 self
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
						(Print 5 17)
					)
					(
						(and
							(== roomWithMotivator curRoomNum)
							(== motivatorState gGGGNorth)
						)
						(claw setLoop: 2)
						(motivator hide:)
						(= roomWithMotivator 0)
						(= motivatorState motivatorGRABBED)
						(= grabberState 5)
						(theMusic number: 76 loop: 1 play:)
						(theGame changeScore: 15)
						(RedrawCast)
						(Print 5 18)
					)
					(else (Print 5 19) (= grabberState 4))
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
