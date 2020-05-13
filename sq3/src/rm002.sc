;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
(include game.sh)
(use Main)
(use Intrface)
(use Timer)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm002 0
)

(local
	grabber
	oldEgoX
	oldEgoY
	local3
	beginningGame
	local5
)
(instance rm002 of Room
	(properties
		picture 2
		horizon 70
		east 5
		south 3
	)
	
	(method (init &tmp [temp0 50])
		(if
			(or
				(== prevRoomNum 777)
				(== prevRoomNum 900)
				(== prevRoomNum 1)
				(== prevRoomNum 155)
				(== (GameIsRestarting) TRUE)
			)
			(music stop:)
			(= beginningGame TRUE)
		)
		(= saveDisabled FALSE)
		(= showStyle HSHUTTER)
		(if (!= prevRoomNum 12) (self setLocales: JUNKBAY))
		(Load VIEW 12)
		(Load VIEW 0)
		(Load VIEW 6)
		(Load SOUND 11)
		(Load SOUND 76)
		(if (== roomWithMotivator curRoomNum)
			(Load VIEW 36)
			(motivator init:)
		)
		(if (== prevRoomNum 12)
			(HandsOff)
			(Load VIEW 258)
			(self setScript: grabScript)
		else
			(Load VIEW 0)
			(TheMenuBar state: TRUE draw:)
			(StatusLine enable:)
			(if beginningGame
				(Load SCRIPT JUMP)
				(= local3 JUMP)
				(Load VIEW 11)
				(Load SOUND 4)
				(Load SOUND 5)
				(Load SOUND 56)
				(ego
					view: 11
					loop: 0
					cel: 0
					setPri: 9
					ignoreActors: 1
					illegalBits: 0
					posn: 141 111
					get: 0
				)
				(HandsOff)
				(self setScript: doorScript)
			)
			(if
			(and debugging (== prevRoomNum 900) (not beginningGame))
				(HandsOn)
				(ego
					view: 0
					setLoop: -1
					loop: 2
					cel: 0
					ignoreActors: TRUE
					illegalBits: cWHITE
					setStep: 3 2
					posn: 142 141
					get: 0
				)
				(= oldEgoX (ego x?))
				(= oldEgoY (ego y?))
				(= global159 0)
				(music number: 11 loop: -1 play:)
			)
			(if (== prevRoomNum 3) (ego x: 170))
			(ego init:)
		)
		(door init:)
		(super init:)
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
	
	(method (dispose)
		(if
			(and
				(!= prevRoomNum 5)
				(!= prevRoomNum 12)
				(!= prevRoomNum 3)
			)
			(DisposeScript 991)
		)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'close/door') (Print 2 0))
					((Said 'look>')
						(cond 
							((Said '<in,through/craft,chute,pane[<escape]') (Print 2 1))
							((Said '/pane') (Print 2 2))
							((Said '/door,door') (Print 2 3))
							((Said '/nozzle') (Print 2 4))
							((Said '/craft,chute[<escape]') (Print 2 5))
							((Said '[<at,around,in][/area,!*]') (Print 2 6))
						)
					)
					((Said 'smell[/area,air]') (Print 2 7))
					((Said 'fix,open,pry/craft,chute,door[<escape]') (Print 2 8))
					(
					(Said 'hoist,manipulate,press,drag/craft,chute[<escape]') (Print 2 9))
					((Said 'begin/chute,craft,engine') (Print 2 10))
					((Said 'break/pane,glass') (Print 2 11))
					((Said 'climb/craft,chute[<escape]') (Print 2 12))
					((Said 'climb') (Print 2 13))
					(
						(or
							(Said 'climb,get<in,in/craft,chute[<escape]')
							(Said 'enter,board[/craft,chute[<escape]]')
						)
						(Print 2 14)
					)
					((Said 'qa')
						(if (not debugging) (event claimed: FALSE) (return))
						(switch
							(= local5
								(Print 2 15
									#mode teJustCenter
									#title {QA-O-Matic}
									#button {Space} 1
									#button {Phleebhut} 2
									#button {Ortega} 3
									#button {Pestulon} 4
									#button {End} 5
								)
							)
							(1
								(= shipRepairLevel 4)
								((inventory at: iReactor) moveTo: 14)
								((inventory at: iWire) moveTo: 14)
								(= roomWithMotivator NULL)
								(= motivatorState motivatorINSHIP)
								(= sittingInCockpit TRUE)
								(= shipLocation shipSPACE)
								(= global207 1)
								(= global208 2)
								(= global206 3)
								(= score 134)
								(theGame changeScore: 1)
								(RedrawCast)
								(curRoom newRoom: 14)
							)
							(2
								(= shipRepairLevel 4)
								((inventory at: iReactor) moveTo: 14)
								((inventory at: iWire) moveTo: 14)
								(= roomWithMotivator NULL)
								(= motivatorState motivatorINSHIP)
								(= shipLocation shipPHLEEBHUT_LAND)
								(= currentSector 39)
								(= scanningSector 39)
								(= global161 3)
								(= gGEgoY_5 4)
								(= global163 3)
								(= global164 4)
								(RedrawCast)
								(curRoom newRoom: 21)
							)
							(3
								(= shipRepairLevel 4)
								((inventory at: iReactor) moveTo: 14)
								((inventory at: iWire) moveTo: 14)
								(= roomWithMotivator NULL)
								(= motivatorState motivatorINSHIP)
								(= shipLocation shipORTEGA_LAND)
								(= currentSector 82)
								(= scanningSector 82)
								(= global161 10)
								(= gGEgoY_5 7)
								(= global163 10)
								(= global164 7)
								(RedrawCast)
								(curRoom newRoom: 21)
							)
							(4
								(= shipRepairLevel 4)
								((inventory at: iReactor) moveTo: 14)
								((inventory at: iWire) moveTo: 14)
								(= roomWithMotivator FALSE)
								(= motivatorState motivatorINSHIP)
								(SetItemOwner iGlowingGem NULL)
								(= shipLocation shipPESTULON_LAND)
								(= currentSector 69)
								(= scanningSector 69)
								(= global161 9)
								(= gGEgoY_5 6)
								(= global163 9)
								(= global164 6)
								(RedrawCast)
								(curRoom newRoom: 21)
							)
							(5
								(= shipRepairLevel 4)
								((inventory at: iReactor) moveTo: 14)
								((inventory at: iWire) moveTo: 14)
								(= roomWithMotivator NULL)
								(= motivatorState motivatorINSHIP)
								(= shipLocation 7)
								(= currentSector 69)
								(= scanningSector 69)
								(= global161 9)
								(= gGEgoY_5 6)
								(= global163 9)
								(= global164 6)
								(RedrawCast)
								(curRoom newRoom: 94)
							)
						)
					)
				)
			)
		)
	)
)

(instance doorScript of Script
	(properties)
	
	(method (doit)
		(if
		(and (== (self state?) 19) (== (doorSound state?) 0))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(ego stopUpd:)
				(= seconds 3)
			)
			(1
				(doorSound play:)
				(= cycles 20)
			)
			(2 (door setCycle: EndLoop self))
			(3
				(door stopUpd:)
				(= cycles 8)
			)
			(4
				(ego setPri: 10 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(5 (= cycles 7))
			(6
				(ego loop: 1 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(7 (= cycles 7))
			(8 (ego setCycle: EndLoop self))
			(9 (= cycles 10))
			(10
				(ego loop: 2 cel: 0 setCycle: 0)
				(= cycles 10)
			)
			(11
				(ego loop: 2 cel: 0 setCycle: CycleTo 1 1 self)
			)
			(12
				(ego
					cycleSpeed: 0
					setStep: -1 10
					setMotion: JumpTo 142 141 self
				)
			)
			(13
				(ego setCycle: EndLoop self)
				(thump play:)
			)
			(14 (= cycles 7))
			(15
				(ego setCel: (- (ego cel?) 1))
				(= cycles 5)
			)
			(16
				(ego
					view: 0
					loop: 2
					cel: 0
					illegalBits: cWHITE
					setStep: -1 2
					setCycle: Walk
					setPri: -1
				)
				(= oldEgoX (ego x?))
				(= oldEgoY (ego y?))
				(= global159 0)
				(= cycles 10)
			)
			(17
				(Print 2 16 #at -1 20 #width 280)
				(door setPri: 9 setCycle: BegLoop self)
				(= cycles 2)
			)
			(18 (doorSound number: 5 play:))
			(19 (door stopUpd:))
			(20
				(door setLoop: 1 forceUpd:)
				(= seconds 2)
			)
			(21
				(HandsOn)
				(music number: 11 loop: -1 play:)
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
					setLoop: (if (== motivatorState motivatorGRABBED) 2 else 0)
					setPri: (if gGGGNorth 13 else 10)
					setStep: 1 2
					x: (if gGGGNorth 166 else 72)
					y: -16
					illegalBits: 0
					ignoreHorizon: 1
					ignoreActors: 1
					init:
					setCycle: Forward
					setMotion: MoveTo (if gGGGNorth 166 else 72) 153 self
				)
			)
			(1
				(if (== gGGGNorth 0) (grabber setPri: 13))
				(Timer set: self 2)
			)
			(2
				(if (== gGGGNorth 0) (grabber setPri: 10))
				(cond 
					((== motivatorState motivatorGRABBED)
						(= grabberState 4)
						(= motivatorState gGGGNorth)
						(motivator init:)
						(= roomWithMotivator curRoomNum)
						(music number: 76 loop: 1 play:)
						(theGame changeScore: -15)
						(RedrawCast)
						(Print 2 17)
					)
					(
					(and (== roomWithMotivator curRoomNum) (== motivatorState gGGGNorth))
						(music number: 76 loop: 1 play:)
						(theGame changeScore: 15)
						(grabber setLoop: 2)
						(motivator hide:)
						(= roomWithMotivator 0)
						(= motivatorState 3)
						(= grabberState 5)
						(RedrawCast)
						(Print 2 18)
					)
					(else (Print 2 19) (= grabberState 4))
				)
				(self changeState: 3)
			)
			(3
				(grabber
					setLoop: (if (== motivatorState 3) 2 else 0)
					setMotion: MoveTo (if gGGGNorth 166 else 72) -16 self
				)
			)
			(4 (curRoom newRoom: 12))
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
			x: (if (== motivatorState motivatorONFLOOR) 183 else 87)
			y: 169
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
					((Said '/motivator,artifact,device') (if motivatorKnown (Print 2 20) else (Print 2 21)))
					(
						(or
							(Said '/plug[<modular,8,spaceware]')
							(Said '/spaceware[<8]')
						)
						(Print 2 22)
					)
					(
						(or
							(Said '[<down,below,at]/dirt,deck')
							(Said '<down,below,at[/dirt,deck]')
						)
						(Print 2 23)
					)
				)
			)
			(
			(Said '(turn<on),begin/motivator,artifact,device') (Print 2 24))
			(
				(Said
					'turn,get,manipulate,press,roll,drag/device,motivator,artifact'
				)
				(Print 2 25)
			)
			(
			(Said 'remove,drag,press,get/plug[<modular,8,spaceware]') (Print 2 26))
		)
	)
)

(instance door of Prop
	(properties
		view 12
	)
	
	(method (init)
		(super init:)
		(self
			cycleSpeed: 1
			setLoop: (if beginningGame 0 else 1)
			setPri:
				(if
				(or (== prevRoomNum 5) (== prevRoomNum 3) (not beginningGame))
					9
				else
					10
				)
			ignoreActors: TRUE
			posn: 138 115
			stopUpd:
		)
	)
)

(instance thump of Sound
	(properties
		number 56
		priority 2
	)
)

(instance doorSound of Sound
	(properties
		number 4
	)
)
