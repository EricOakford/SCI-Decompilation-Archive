;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
(include game.sh)
(use Main)
(use Intrface)
(use Reverse)
(use Timer)
(use Avoider)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm008 0
)

(local
	climbedUpMallard
	ladderAtMallard
	local2
	oldLoop
	clawX
	clawY
	printObj
	local7
	local8
)
(instance rm008 of Room
	(properties
		picture 8
		style HSHUTTER
		horizon 1
	)
	
	(method (init &tmp [temp0 50])
		(HandsOff)
		(= useSortedFeatures TRUE)
		(= egoBlindSpot 120)
		(if (!= prevRoomNum 11)
			(self
				setLocales: JUNKBAY
				setFeatures: thePod theMallard
				setScript: rmScript
			)
		)
		(Load SCRIPT JUMP)
		(= local7 JUMP)
		(Load VIEW 16)
		(Load VIEW 6)
		(Load VIEW 19)
		(Load VIEW 21)
		(Load VIEW 29)
		(Load VIEW 257)
		(Load VIEW 750)
		(Load VIEW 193)
		(Load SOUND 11)
		(Load SOUND 56)
		(if
			(and
				(!= motivatorState 4)
				(== roomWithMotivator curRoomNum)
			)
			(Load VIEW 36)
			(motivator init:)
		)
		(cond 
			((== prevRoomNum 14)
				(= ladderAtMallard TRUE)
				((inventory at: iLadder) moveTo: 8)
				(theMusic number: 11 loop: -1 play:)
			)
			((== prevRoomNum 11)
				(Load VIEW 258)
				(Load SOUND 76)
				(= clawX (if gGGGNorth 215 else 51))
				(= clawY (if gGGGNorth 67 else 139))
			)
			(else
				(ego
					view: 19
					setLoop: 0
					illegalBits: 0
					ignoreHorizon: 1
					posn: 251 14
					setStep: 1 1
					setPri: -1
					init:
					moveSpeed: 1
					cycleSpeed: 1
					setCycle: Reverse
					setMotion: MoveTo 251 47 rmScript
					setScript: rmScript
				)
			)
		)
		(if (InRoom iLadder) (ladder init: stopUpd:))
		(hatch init:)
		(super init:)
		(if (== prevRoomNum 11) (self setScript: grabScript))
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
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (handleEvent event podMsg &tmp [temp0 50])
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '/scrap,iron,debris,garbage') (Print 8 0))
					((Said '/handle[<craft,aluminum,door]') (if climbedUpMallard (Print 8 1) else (Print 8 2)))
					((Said '/chute,(craft<little)')
						(switch (= podMsg (Random 1 2))
							(1 (Print 8 3))
							(2 (Print 8 4))
						)
					)
					((Said '/aluminum,craft[<big]') (Print 8 5))
					((Said '/nozzle') (Print 8 6))
					((Said '/engine') (Print 8 7))
					((Said '/neck,android,head') (Print 8 8))
					(
						(and
							(not climbedUpMallard)
							(Said
								'[<in,through,in,in]/cavity[<chute,little,cavity,pyramid]'
							)
						)
						(if (ego inRect: 2 64 91 109)
							(Print 8 9)
						else
							(Print 8 10)
						)
					)
					(
						(or
							(Said '<in,in,in/chute,craft')
							(Said '[<at,in,through,in]/pane,port,(cavity<port)')
							(Said '/cabin,chair[<scum]')
						)
						(cond 
							((ego inRect: 44 105 87 116) (Print 8 11))
							((ego inRect: 59 117 170 162) (Print 8 12))
							(else (NotClose))
						)
					)
					((Said '/console[<craft]')
						(if (ego inRect: 59 117 170 162)
							(Print 8 13)
						else
							(Print 8 14)
						)
					)
					(
					(Said '[<in,in,in]/motivator,cavity,compartment')
						(if (and climbedUpMallard (ego inRect: 193 75 260 89))
							(if (== motivatorState 4)
								(Print 8 15)
							else
								(Print 8 16)
							)
						else
							(Print 8 17)
						)
					)
					(
						(or
							(Said '/plug[<modular,8,spaceware]')
							(Said '/spaceware[<8]')
						)
						(if climbedUpMallard
							(if (== roomWithMotivator 4)
								(Print 8 18)
							else
								(Print 8 19)
							)
						else
							(Print 8 20)
						)
					)
					((Said '/leggo,domino,artifact') (Print 8 21))
					((Said '/ladder')
						(if (InRoom iLadder)
							(Print 8 22)
						else
							(event claimed: FALSE)
						)
					)
					((Said '/door') (if climbedUpMallard (Print 8 23) else (Print 8 20)))
					((Said '/stair') (Print 8 24))
					((Said '/tube,hose,conduit') (Print 8 25))
					((Said '[<around,at,in][/area,!*]') (Print 8 26))
				)
			)
			(
			(Said '(play[<with]),use[/leggo,domino,artifact]') (Print 8 27))
			(
				(Said
					'hoist,manipulate,press,drag/craft,chute,aluminum[<escape,big,little]'
				)
				(Print 8 28)
			)
			((Said 'get>')
				(cond 
					((Said '/plug[<modular]') (if climbedUpMallard (Print 8 29) else (event claimed: FALSE)))
					((Said '/leggo,domino,artifact') (Print 8 30))
					((Said '/ladder')
						(if (InRoom iLadder)
							(cond 
								((and ladderOnGround (ego inRect: 220 115 274 140))
									(ego get: iLadder)
									(ladder dispose:)
									(theGame changeScore: -5)
									(Print 8 31)
									(Print 8 32)
									(= ladderOnGround FALSE)
								)
								((ego inRect: 215 114 226 121)
									(ego get: iLadder)
									(ladder dispose:)
									(theGame changeScore: -5)
									(Print 8 31)
									(Print 8 32)
								)
								(climbedUpMallard (Print 8 33))
								(else (NotClose))
							)
						else
							(Print 8 34)
						)
					)
				)
			)
			((Said 'enter/!*') (Print 8 35))
			((Said 'call/diagnostic') (Print 8 36))
			(
				(and
					(ego inRect: 193 75 260 89)
					(Said 'enter/cavity,compartment')
				)
				(Print 8 37)
			)
			(
				(Said
					'use,erect,drop,stand,place[<up,on]/ladder/chute,(craft<little)'
				)
				(Print 8 38)
			)
			(
				(or
					(Said 'open,enter/door[<craft,aluminum]')
					(Said 'turn/handle[<craft,aluminum,door]')
					(Said 'get<in[/craft,aluminum]')
					(Said 'board,enter,(get<in)/craft,aluminum')
					(Said 'climb<in,through/door')
				)
				(if climbedUpMallard
					(if (ego inRect: 165 77 208 96)
						(hatchScript changeState: 1)
					else
						(NotClose)
					)
				else
					(Print 8 2)
				)
			)
			(
				(or
					(Said 'open,enter,(get<in,in)/door<chute,(craft<little)')
					(Said 'climb/chute,(craft<little,escape)')
				)
				(Print 8 39)
			)
			((Said 'cable,generator') (Print 8 40))
			(
				(or
					(Said 'plug,afix/motivator,device,artifact')
					(Said 'use/plug')
				)
				(Print 8 41)
			)
			((Said 'close/door') (Print 8 42))
			(
				(Said
					'use,erect,drop,stand,place[<up,on,down]/ladder[/craft]'
				)
				(if (ego has: iLadder)
					(cond 
						((ego inRect: 175 105 270 168) (ego setScript: ladderScript))
						((ego inRect: 2 64 91 109) (Print 8 43))
						(else (Print 8 44))
					)
				else
					(Print 8 45)
				)
			)
			(
				(Said
					'use,get,climb/debris,scrap,iron,artifact,domino,pole,blade'
				)
				(if (ego inRect: 17 122 61 155)
					(ego setScript: bleedScript)
				else
					(Print 8 46)
				)
			)
			(
				(Said
					'climb,crawl[<through,in,in]/pane,port,(cavity<port)'
				)
				(Print 8 47)
			)
			(
				(or
					(Said
						'enter,(climb<in,through,in,in)/cavity[<chute,little]'
					)
					(Said
						'reach,appendage<in,in,in/cavity,chute,craft[<little]'
					)
					(Said
						'reach,appendage<in,in,in/appendage/cavity,chute,craft[<little]'
					)
				)
				(if (ego inRect: 2 64 91 109)
					(Print 8 48)
				else
					(NotClose)
				)
			)
			((Said 'descend,climb[<down,up]/ladder')
				(cond 
					((InRoom iLadder)
						(cond 
							((== (ladder cel?) 1) (ego setScript: climbLadderScript))
							((== (ladder cel?) 2) (Print 8 49))
							(else (Print 8 50))
						)
					)
					((ego has: iLadder) (Print 8 51))
					(else (Print 8 52))
				)
			)
			((Said 'climb[<!*][/!*]')
				(cond 
					((ego inRect: 241 48 261 55) (rmScript changeState: 2))
					(
						(or
							(ego inRect: 193 73 222 102)
							(ego inRect: 215 114 226 121)
						)
						(if (InRoom iLadder)
							(cond 
								((== (ladder cel?) 1) (ego setScript: climbLadderScript))
								((== (ladder cel?) 2) (Print 8 49))
								(else (Print 8 50))
							)
						else
							(Print 8 53)
						)
					)
					(else (Print 8 54))
				)
			)
			((Said 'climb[<in]/chute') (Print 8 55))
			(
				(or
					(Said 'climb[<down,off]/ladder,craft,aluminum,aluminum')
					(Said 'climb<down,off')
					(Said 'descend/ladder,craft,aluminum,aluminum')
				)
				(if (ego inRect: 193 73 222 102)
					(if (InRoom iLadder)
						(cond 
							((== (ladder cel?) 1) (ego setScript: climbLadderScript))
							((== (ladder cel?) 2) (Print 8 49))
							(else (Print 8 50))
						)
					else
						(Print 8 53)
					)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'climb,(climb<up)[/!*]')
				(cond 
					((ego inRect: 241 48 261 55) (rmScript changeState: 2))
					((ego inRect: 215 114 226 121)
						(if (InRoom iLadder)
							(cond 
								((== (ladder cel?) 1) (ego setScript: climbLadderScript))
								((== (ladder cel?) 2) (Print 8 49))
								(else (Print 8 50))
							)
						else
							(Print 8 53)
						)
					)
					(else (Print 8 54))
				)
			)
			((Said 'descend,(climb<down)[/!*]')
				(if (ego inRect: 193 73 222 102)
					(if (InRoom 2)
						(cond 
							((== (ladder cel?) 1) (ego setScript: climbLadderScript))
							((== (ladder cel?) 2) (Print 8 49))
							(else (Print 8 50))
						)
					else
						(Print 8 53)
					)
				else
					(Print 8 54)
				)
			)
		)
		(if
		(Said 'climb,climb/neck,head,android,nerve,cable')
			(if (ego inRect: 241 48 261 55)
				(rmScript changeState: 2)
			else
				(NotClose)
			)
		)
		(if
			(or
				(Said 'climb[<up,on,to]/neck,head,android,nerve,cable')
				(Said 'climb<up,on')
				(Said 'climb/neck,head,android,nerve,cable')
			)
			(if (ego inRect: 241 48 261 55)
				(rmScript changeState: 2)
			else
				(event claimed: 0)
			)
		)
		(if
			(or
				(Said 'climb[<up,on]/ladder,craft,aluminum,aluminum')
				(Said 'climb<up,on')
				(Said 'climb/ladder,craft,aluminum,aluminum')
			)
			(if (ego inRect: 215 114 226 121)
				(if (InRoom iLadder)
					(cond 
						((== (ladder cel?) 1) (ego setScript: climbLadderScript))
						((== (ladder cel?) 2) (Print 8 49))
						(else (Print 8 50))
					)
				else
					(Print 8 56)
				)
			else
				(Print 8 54)
			)
		)
		(if (Said 'break/pane,glass') (Print 8 57))
	)
)

(instance rmScript of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(not isHandsOff)
				(not programControl)
				(== climbedUpMallard TRUE)
			)
			(if
				(or
					(== (ego onControl: 0) 8193)
					(== (ego onControl: 0) -24576)
					(== (ego onControl: 0) -24575)
					(== (ego onControl: 0) 24577)
				)
				(= local2 1)
			)
			(if
				(or
					(== (ego onControl: 0) 4097)
					(== (ego onControl: 0) 20481)
					(== (ego onControl: 0) -12287)
				)
				(if (< (ego x?) 175) (= local2 3) else (= local2 2))
			)
			(if (> local2 0)
				(= climbedUpMallard FALSE)
				(HandsOff)
				(self changeState: 4)
				(= programControl TRUE)
				(= oldLoop (ego loop?))
			)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(1
				(HandsOn)
				(= programControl FALSE)
				(ego
					view: 0
					setCycle: Walk
					setLoop: -1
					loop: 2
					moveSpeed: 0
					cycleSpeed: 0
					setStep: 3 2
				)
				(if (!= ladderAtMallard TRUE)
					(ego posn: 250 54 setPri: -1 illegalBits: -16384)
				else
					(= climbedUpMallard TRUE)
					(ego posn: 207 86 illegalBits: 0)
					(= ladderAtMallard FALSE)
				)
			)
			(2
				(HandsOff)
				(= programControl 1)
				(ego
					view: 19
					setLoop: 0
					setStep: 1 1
					illegalBits: 0
					posn: 251 47
					setMotion: MoveTo 251 13 self
					setCycle: Forward
					moveSpeed: 1
					cycleSpeed: 1
				)
			)
			(3 (curRoom newRoom: 7))
			(4
				(ego
					setMotion: 0
					view: 257
					setLoop: (ego loop?)
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(5
				(ego
					setStep: -1 10
					setCycle: 0
					ignoreActors:
					illegalBits: 0
				)
				(if (== local2 1)
					(ego setPri: 4)
					(if (< (ego x?) 175)
						(ego setMotion: JumpTo (ego x?) 134 self)
					else
						(ego setMotion: JumpTo (ego x?) 110 self)
					)
				)
				(if (== local2 3)
					(ego
						setStep: 10
						setMotion: MoveTo (- (ego x?) 32) 138 self
					)
				)
				(if (== local2 2)
					(ego
						setStep: -1 15
						setMotion: JumpTo (+ (ego x?) 10) (+ (ego y?) 30) self
					)
				)
			)
			(6
				(cond 
					((== local2 3)
						(ego view: 750 setLoop: 1 setCel: 2)
						(Timer setReal: self 3)
						(self state: (+ state 1))
						(thump play:)
					)
					((== local2 2)
						(ego view: 16 loop: 0 cel: 0 stopUpd:)
						(Timer setCycle: self 2)
					)
					(else (ego hide:) (Timer set: self 2))
				)
			)
			(7
				(Print 8 58)
				(EgoDead 901 0 15 1)
				(HandsOn)
			)
			(8
				(ego
					view: 0
					setStep: 3 2
					setLoop: -1
					setPri: -1
					setCycle: Walk
					cycleSpeed: 0
					ignoreActors: 0
					illegalBits: -16384
				)
				(= programControl FALSE)
				(if (not ladderOnGround) (ladder setPri: 8 forceUpd:))
				(= local2 0)
				(= climbedUpMallard FALSE)
				(RedrawCast)
				(Print 8 59)
				(HandsOn)
			)
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
					setLoop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
				(= printObj (Print 8 60 #dispose))
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
				(Print 8 61 #at -1 20 #width 280)
				(EgoDead 901 0 6 8)
			)
		)
	)
)

(instance hatchScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== ladderAtMallard TRUE)
					(self changeState: 2)
				else
					(hatch stopUpd:)
				)
			)
			(1
				(ego setMotion: 0 posn: 205 86)
				(HandsOff)
				(Print 8 62)
				(= cycles 2)
			)
			(2
				(hatch startUpd: setCycle: EndLoop self)
			)
			(3
				(if (== ladderAtMallard TRUE) (self state: (+ state 1)))
				(= seconds 2)
			)
			(4
				(ego
					view: 21
					posn: 202 87
					setLoop: 1
					cel: 0
					setMotion: 0
					setCycle: EndLoop self
					cycleSpeed: 1
				)
				(self state: (+ state 1))
			)
			(5
				(ego
					view: 21
					posn: 202 87
					setLoop: 1
					cel: 7
					init:
					setPri: 9
					setMotion: 0
					setCycle: BegLoop self
					cycleSpeed: 1
				)
			)
			(6
				(if (not ladderAtMallard)
					(theMusic fade:)
					(if (not enteredMallard)
						(= enteredMallard TRUE)
						(theGame changeScore: 10)
					)
					(curRoom newRoom: 14)
				else
					(= cycles 5)
				)
			)
			(7
				(hatch setCycle: BegLoop self)
				(rmScript changeState: 1)
			)
			(8 (hatch stopUpd:))
		)
	)
)

(instance ladderScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(curRoom setScript: rmScript)
				(ego setAvoider: Avoider setMotion: MoveTo 220 120 self)
			)
			(1
				(PutInRoom iLadder curRoomNum)
				(theGame changeScore: 5)
				(ego setAvoider: 0 loop: 1)
				(ladder init: ignoreActors: TRUE stopUpd:)
				(DisposeScript AVOIDER)
				(HandsOn)
			)
		)
	)
)

(instance climbLadderScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(= programControl TRUE)
				(HandsOff)
				(ego
					view: 19
					setLoop: 0
					illegalBits: 0
					ignoreActors: TRUE
					x: 220
					y: (if climbedUpMallard 103 else 119)
					setPri: 9
				)
				(if (not climbedUpMallard)
					(ego setMotion: MoveTo 220 103 self)
				else
					(ladder setPri: 8 forceUpd:)
					(ego setMotion: MoveTo 220 119 self setCycle: Reverse)
				)
			)
			(1
				(HandsOn)
				(= programControl FALSE)
				(ego
					view: 0
					setLoop: -1
					setCycle: Walk
					setStep: 3 2
					ignoreActors: 0
				)
				(if (not climbedUpMallard)
					(= climbedUpMallard TRUE)
					(ladder setPri: 10 forceUpd:)
					(ego setPri: 9 illegalBits: 0 posn: 216 89)
					(RedrawCast)
					(curRoom setScript: rmScript)
					(Print 8 63)
				else
					(ego setPri: -1 illegalBits: -16384 posn: 220 120)
					(= local2 0)
					(= climbedUpMallard FALSE)
				)
			)
		)
	)
)

(instance grabScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(claw init: setMotion: MoveTo clawX clawY self)
			)
			(1 (= cycles 15))
			(2
				(cond 
					((== motivatorState motivatorGRABBED)
						(if (== gGGGNorth 1)
							(theMusic number: 76 loop: 1 play:)
							(theGame changeScore: 15)
							(RedrawCast)
							(Print 8 64)
							(= motivatorState motivatorINSHIP)
							(= roomWithMotivator gGGGNorth)
						else
							(theMusic number: 76 loop: 1 play:)
							(theGame changeScore: -15)
							(RedrawCast)
							(Print 8 65)
							(motivator init:)
							(= roomWithMotivator curRoomNum)
							(= motivatorState gGGGNorth)
						)
						(= grabberState 4)
					)
					(
						(and
							(== motivatorState gGGGNorth)
							(or
								(== roomWithMotivator curRoomNum)
								(== motivatorState 4)
							)
						)
						(claw setLoop: 2)
						(if (!= gGGGNorth 1) (motivator hide:))
						(= roomWithMotivator 0)
						(= motivatorState motivatorGRABBED)
						(= grabberState 5)
						(theMusic number: 76 loop: 1 play:)
						(theGame changeScore: 15)
						(RedrawCast)
						(Print 8 66)
					)
					(else (Print 8 67) (= grabberState 4))
				)
				(= cycles 2)
			)
			(3
				(claw
					setLoop: (if (== motivatorState motivatorGRABBED) 2 else 0)
					setMotion: MoveTo clawX -19 self
				)
			)
			(4 (curRoom newRoom: 11))
		)
	)
)

(instance ladder of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 29
			loop: 0
			cel: (if ladderOnGround 2 else 1)
			ignoreActors: TRUE
			posn: (if ladderOnGround 242 else 219) (if ladderOnGround 131 else 114)
			setPri: (cond 
				(ladderOnGround 4)
				(climbedUpMallard 10)
				(else 8)
			)
		)
	)
)

(instance hatch of Prop
	(properties
		view 21
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors: TRUE
			setPri: 8
			posn: 188 89
			setScript: hatchScript
			cycleSpeed: 1
		)
	)
)

(instance claw of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 258
			setLoop: (if (== motivatorState motivatorGRABBED) 2 else 0)
			setPri: 15
			setStep: 1 2
			illegalBits: 0
			ignoreHorizon: TRUE
			ignoreActors: TRUE
			posn: clawX -19
			setCycle: Forward
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
			posn: 66 155
			setStep: 1 3
			setPri: 11
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
					((Said '/motivator,artifact,device') (if motivatorKnown (Print 8 68) else (Print 8 69)))
					((Said '/plug[<modular,8,spaceware]') (Print 8 70))
					((Said '[<down,below,at][/dirt,deck]') (Print 8 71))
				)
			)
			(
				(Said
					'remove,turn,get,manipulate,press,roll,drag/motivator,artifact,device'
				)
				(Print 8 72)
			)
			(
				(Said
					'(turn<on),begin/motivator,artifact,device[<little,round]'
				)
				(Print 8 73)
			)
			(
			(Said 'remove,drag,press,get/plug[<modular,8,spaceware]') (Print 8 74))
		)
	)
)

(instance thump of Sound
	(properties
		number 56
		priority 2
	)
)

(instance thePod of Feature
	(properties
		y 104
		x 54
	)
	
	(method (handleEvent event podMsg)
		(cond 
			(
			(or (event claimed?) (!= (event type?) saidEvent)) (return))
			((Said 'look/craft,chute[<little]')
				(switch (= podMsg (Random 1 2))
					(1 (Print 8 75))
					(2 (Print 8 76))
				)
			)
		)
	)
)

(instance theMallard of Feature
	(properties
		y 106
		x 187
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (event claimed?) (!= (event type?) saidEvent)) (return))
			((Said 'look/craft,aluminum[<big]') (Print 8 5))
		)
	)
)
