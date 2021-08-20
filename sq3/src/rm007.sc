;;; Sierra Script 1.0 - (do not remove this comment)
(script# 7)
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
	rm007 0
)

(local
	local0
	claw
)
(instance rm007 of Room
	(properties
		picture 7
		style HSHUTTER
		horizon 10
		west 6
	)
	
	(method (init &tmp [temp0 50])
		(if (== prevRoomNum 11)
			(Load VIEW 258)
			(Load SOUND 76)
		else
			(self setLocales: JUNKBAY)
			(Load VIEW 0)
			(Load VIEW 6)
			(Load VIEW 14)
			(Load VIEW 17)
			(Load SOUND 45)
		)
		(if (== roomWithMotivator curRoomNum)
			(Load VIEW 36)
			(motivator init:)
		)
		(if (== prevRoomNum 6)
			(ego view: 0)
		else
			(Load SOUND 56)
		)
		(super init:)
		(self setScript: rmScript)
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
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '[<at,around,in][/area,!*]') (Print 7 0))
					((Said '<in,at,in,in/craft,stage,wreck') (Print 7 1))
					((Said '/craft,stage,wreck') (Print 7 2))
					((Said '/head,android,artifact,helmet') (Print 7 3))
					((Said '/eye[<broken,glass,android]') (Print 7 4))
					((Said '<in,in,in/android,eye[<broken,glass]') (Print 7 5))
					((Said '[<at,in,through,in]/grate') (Print 7 6))
					((Said '/whip,(programmer<dense,metallic)') (Print 7 7))
					(
						(or
							(Said '[<down,below,at]/dirt,deck')
							(Said '<down,below,at[/dirt,deck]')
						)
						(Print 7 8)
					)
					((Said '/down,(edge[<deck])')
						(if (ego inRect: 32 156 192 189)
							(Print 7 9)
						else
							(NotClose)
						)
					)
				)
			)
			((Said 'craft') (Print 7 10))
			(
				(Said
					'enter,climb,crawl,go[<on,in,in,through,up,in][/android,eye,head,pane[<broken,to]]'
				)
				(if (ego inRect: 160 163 178 180)
					(rmScript changeState: 4)
				else
					(Print 7 11)
				)
			)
			(
			(Said 'enter,climb[<in,in,up,in]/grate[<android]') (Print 7 12))
			(
			(Said 'manipulate,press,drag,hoist/head,android,helmet') (Print 7 13))
			((Said 'climb<down') (Print 7 14))
			((Said 'get/head') (Print 7 15))
			((Said 'give/head') (Print 7 16))
			((Said 'break,remove/android,eye[<broken,glass]') (Print 7 17))
		)
	)
	
	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (doit)
		(if
		(and (== (ego onControl: 0) 2) (!= (ego view?) 17))
			(self changeState: 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== prevRoomNum 11)
					(self setScript: grabScript)
				else
					(ego setStep: -1 2)
					(if (== prevRoomNum 8)
						(ego
							view: 14
							cel: (ego lastCel:)
							illegalBits: 0
							posn: 195 171
							setPri: 13
							init:
							cycleSpeed: 1
						)
						(self changeState: 6)
					else
						(ego posn: 9 113 init:)
					)
				)
			)
			(1
				(HandsOff)
				(ego
					illegalBits: 0
					view: 17
					cel: 0
					setLoop: 2
					cycleSpeed: 2
					setCycle: EndLoop self
					setMotion: 0
				)
			)
			(2
				(thump number: 45 play:)
				(ego
					setCycle: 0
					setStep: -1 15
					setMotion: MoveTo (ego x?) 240 self
				)
			)
			(3
				(Print 7 18)
				(EgoDead 901 0 0 1)
				(return)
			)
			(4
				(HandsOff)
				(ego
					illegalBits: 0
					view: 14
					cel: 0
					posn: 195 171
					setPri: 13
					setCycle: EndLoop self
					setMotion: 0
					cycleSpeed: 1
				)
			)
			(5
				(if (not climbedDownBattlebot)
					(= climbedDownBattlebot TRUE)
					(theGame changeScore: 5)
				)
				(curRoom newRoom: 8)
			)
			(6
				(ego cycleSpeed: 1 setCycle: BegLoop self)
			)
			(7
				(thump number: 56 play:)
				(HandsOn)
				(ego
					view: 0
					setLoop: -1
					setPri: -1
					posn: 168 169
					loop: 2
					cel: 0
					setCycle: Walk
					setStep: 3 2
					illegalBits: -32768
					moveSpeed: 0
					cycleSpeed: 0
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
			setStep: 1 1
			illegalBits: 0
			x: (if (== motivatorState motivatorONFLOOR) 217 else 119)
			y: 81
			setPri: 4
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
					((Said '/motivator,artifact,device') (if motivatorKnown (Print 7 19) else (Print 7 20)))
					(
						(or
							(Said '/plug[<modular,8,spaceware]')
							(Said '/spaceware[<8]')
						)
						(Print 7 21)
					)
					((Said '[<down,below,at][/dirt,deck]') (Print 7 22))
				)
			)
			(
				(Said
					'remove,get,manipulate,press,roll,drag/motivator,artifact,device'
				)
				(Print 7 23)
			)
			(
			(Said '(turn<on),begin/motivator,artifact,device') (Print 7 24))
			(
			(Said 'remove,press,drag,get/plug[<modular,8,spaceware]') (Print 7 25))
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
					setPri: 5
					setStep: 1 2
					x: (if gGGGNorth 202 else 105)
					y: -19
					illegalBits: 0
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					init:
					setCycle: Forward
					setMotion: MoveTo (if gGGGNorth 202 else 105) 65 self
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
						(Print 7 26)
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
						(Print 7 27)
					)
					(else (Print 7 28) (= grabberState 4))
				)
				(self changeState: 3)
			)
			(3
				(claw
					setLoop: (if (== motivatorState motivatorGRABBED) 2 else 0)
					setMotion: MoveTo (claw x?) -19 self
				)
			)
			(4 (curRoom newRoom: 11))
		)
	)
)

(instance thump of Sound
	(properties
		number 56
		priority 5
	)
)
