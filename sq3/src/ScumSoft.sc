;;; Sierra Script 1.0 - (do not remove this comment)
(script# 702)
(include game.sh)
(use Main)
(use Intrface)
(use Chase)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	scumSoft 0
)

(local
	scriptClient
	local1
	local2
	local3
	[droidPath 63] = [0 10 161 0 41 160 0 74 154 1 101 149 1 123 145 1 145 136 2 166 128 2 182 110 2 194 98 3 203 75 3 208 55 3 203 34 3 191 17 4 174 8 4 159 11 4 146 16 4 136 27 5 136 40 5 140 51 5 148 54 5 161 57]
)
(instance scumSoft of Region
	(properties)
	
	(method (init)
		(StatusLine enable:)
		(super init: &rest)
		(= egoBlindSpot 0)
		(HandsOn)
		(Load VIEW 92)
		(Load VIEW 0)
		(Load VIEW 256)
		(Load VIEW 113)
		(Load VIEW 114)
		(Load VIEW 115)
		(Load SOUND 46)
		(Load SOUND 47)
		(Load SOUND 44)
		(Load SOUND 55)
		(Load SOUND 84)
		(Load SOUND 102)
		(Load SOUND 103)
		(jelloSound init:)
		(vaporizeSound init:)
		(if (not (== curRoomNum 93))
			(music number: 102 loop: -1 play:)
		else
			(music stop:)
		)
		(announce state: scumSoftAnnouncement)
		(if scumSoftAlerted
			(if (== prevRoomNum 90)
				(= scumSoftAlerted 0)
				(= shadowDroidX 0)
				(= shadowDroidY 0)
			else
				(if
					(and
						(== 0 shadowDroidX)
						(== shadowDroidX shadowDroidY)
					)
					(= shadowDroidX 166)
					(= shadowDroidY 97)
				)
				(User canInput: FALSE)
				(droidScript changeState: 1)
			)
		)
	)
	
	(method (doit)
		(if
		(and (not scumSoftAlerted) (== 700 (Random 1 1400)))
			(announce cue:)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					(
						(Said
							'look[/area,department,deck,dirt,ceiling,left,right,up,down]'
						)
						(Print 702 0)
					)
					((Said 'look/partition,partition') (Print 702 1))
					((Said '*/cabinet,cabinet') (Print 702 2))
					(
						(Said
							'converse,look/man,folk,bystander,eightprong,accountant'
						)
						(Print 702 3)
					)
					((Said 'look/garbage,can,basket') (Print 702 4))
					(
						(Said
							'look/chart,paper,passenger,calendar,board,desk,chair'
						)
						(Print 702 5)
					)
					(
						(Said
							'sit,explore,get,beat,beat,get,feel/chart,passenger,calendar,board,chair,man,desk,paper,partition'
						)
						(Print 702 6)
					)
				)
			)
		)
	)
)

(instance announce of Script
	(properties)
	
	(method (changeState newState)
		(++ scumSoftAnnouncement)
		(switch (= state newState)
			(1 (Print 702 7))
			(2 (Print 702 8))
		)
	)
)

(class TrashBasket of Prop
	(properties
		vaporized 0
		misses 0
		egoHere 0
		egoNear 0
		myNerd 0
		myID 0
		nearWest 0
		nearNorth 0
		nearEast 0
		nearSouth 0
	)
	
	(method (init)
		(self
			vaporized: [global567 myID]
			view: 115
			ignoreActors: 0
			setLoop: 4
			setCel: [global567 (super init:)]
			stopUpd:
		)
		(if (and vaporized myID) (self addToPic:))
	)
	
	(method (doit)
		(cond 
			(scumSoftAlerted (self egoNear: FALSE))
			(
				(and
					(<= nearWest (ego x?))
					(<= (ego x?) nearEast)
					(<= nearNorth (ego y?))
					(<= (ego y?) nearSouth)
				)
				(if
					(and
						(ego has: iCoveralls)
						(ego has: iVaporizer)
						(not vaporized)
						myNerd
					)
					(self egoNear: TRUE)
				)
			)
			((and egoNear (not vaporized) myNerd) (self egoNear: FALSE) (alertScript init: (self myNerd?)))
			(
				(and
					(not (ego mover?))
					(not global243)
					(not scumSoftAlerted)
					myNerd
					(== 50 (Random 1 100))
				)
				(myNerd setCel: (if (myNerd cel?) 0 else 3))
				(if
				(and (not (== curRoomNum 93)) (== 1 (Random 1 2)))
					(music
						number: (if (== 1 (Random 1 2)) 103 else 102)
						play:
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (ego has: 13)
					(cond 
						((Said 'look/garbage<mr') ((inventory at: iVaporizer) showSelf:))
						(
							(or
								(Said 'blast[/garbage]')
								(Said 'use/mrgarbage')
								(Said 'use/garbage<mr')
							)
							(if (self perform: egoIsHere species) (self egoHere: TRUE))
							(self vaporize:)
						)
						((Said 'blast/*') (Print 702 9))
					)
				)
			)
		)
	)
	
	(method (cue)
		(cond 
			(egoHere
				(if vaporized
					(Print 702 10)
				else
					(self
						vaporized: TRUE
						setCel: (= [global567 myID] 1)
						misses: 0
					)
					(cond 
						((not (== curRoomNum 93)) (self addToPic:))
						((not myNerd) (Print 702 11))
					)
				)
				(self egoHere: 0)
			)
			(egoNear
				(switch (++ misses)
					(1 (Print 702 12))
					(2 (Print 702 13))
					(3 (Print 702 14))
					(4 (Print 702 15))
					(5 (Print 702 16))
					(6
						(if scumSoftAlerted
							(-- misses)
						else
							(self egoNear: 0)
							(alertScript init: (self myNerd?))
						)
					)
				)
			)
			(else (Print 702 17))
		)
		(ego view: 113 cycleSpeed: 0 setCycle: Walk)
		(HandsOn)
	)
	
	(method (vaporize)
		(HandsOff)
		(ego view: 114 setCel: 0 setCycle: EndLoop self)
		(vaporizeSound play:)
	)
)

(instance egoIsHere of Code
	(properties)
	
	(method (doit param1)
		(if scumSoftAlerted (return FALSE))
		(switch (ego loop?)
			(0
				(if
					(or
						(> (ego brTop?) (param1 brBottom?))
						(< (ego brBottom?) (param1 brTop?))
						(> (ego brRight?) (param1 brLeft?))
						(< (+ (ego brRight?) (ego xStep?)) (param1 brLeft?))
					)
					(return FALSE)
				)
			)
			(1
				(if
					(or
						(> (ego brTop?) (param1 brBottom?))
						(< (ego brBottom?) (param1 brTop?))
						(< (ego brLeft?) (param1 brRight?))
						(> (- (ego brLeft?) (ego xStep?)) (param1 brRight?))
					)
					(return FALSE)
				)
			)
			(2
				(if
					(or
						(< (ego brRight?) (param1 brLeft?))
						(> (ego brLeft?) (param1 brRight?))
						(> (ego brBottom?) (param1 nsTop?))
						(< (+ (ego brBottom?) (ego yStep?)) (param1 nsTop?))
					)
					(return FALSE)
				)
			)
			(3
				(if
					(or
						(< (ego brRight?) (param1 brLeft?))
						(> (ego brLeft?) (param1 brRight?))
						(< (ego brTop?) (param1 nsBottom?))
						(> (- (ego brTop?) (ego yStep?)) (param1 nsBottom?))
					)
					(return FALSE)
				)
			)
		)
		(return TRUE)
	)
)

(class Nerd of Prop
	(properties
		seeProblem 0
		speakX 0
		speakY 0
		speakCel 0
	)
	
	(method (init)
		(super init:)
		(self view: 115 setCel: 0 stopUpd:)
	)
)

(class Fink of Actor
	(properties
		seeProblem 0
		speakX 0
		speakY 0
		speakCel 0
	)
	
	(method (init)
		(super init:)
		(self view: 115 xStep: 4 stopUpd:)
	)
	
	(method (doit)
		(super doit:)
		(if seeProblem
			(if scumSoftAlerted
				(= seeProblem FALSE)
			else
				(alertScript init: self)
			)
		)
	)
)

(instance alertScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: FALSE)
				(= scumSoftAlerted TRUE)
				(ego setMotion: 0)
				(= scriptClient (self client?))
				(alertBalloon init:)
				(music number: 84 loop: -1 play:)
				(= local1 5)
				(if (> (scriptClient loop?) 3)
					(self cue:)
				else
					(scriptClient setCycle: EndLoop self)
				)
			)
			(1
				(if (and (-- local1) (not (ego mover?)))
					(-- state)
					(Timer setReal: self 2)
				else
					(= cycles 1)
				)
			)
			(2
				(if (> (scriptClient loop?) 3)
					(= cycles 1)
				else
					(scriptClient setCycle: BegLoop self)
				)
			)
			(3
				(scriptClient stopUpd:)
				(alertBalloon dispose:)
				(if (< (scriptClient loop?) 3) (Print 702 18))
				(curRoom setScript: droidScript)
			)
		)
	)
)

(instance alertBalloon of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			posn: (scriptClient speakX?) (scriptClient speakY?)
			view: 115
			setLoop: (if (== curRoomNum 93) 9 else 5)
			setCel: (scriptClient speakCel?)
			setPri: 15
			stopUpd:
		)
	)
)

(instance shadowDroid of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			setPri: 0
			view: 256
			setLoop: 2
			setCel: 1
			setCycle: Forward
			xStep: (if (ego has: iCoveralls) 3 else 8)
			yStep: (if (ego has: 12) 2 else 5)
			ignoreHorizon: 1
			ignoreActors: 1
			ignoreControl: -1
		)
		(cond 
			(
				(and
					(== shadowDroidX shadowDroidY)
					(== shadowDroidY 0)
				)
				(self
					posn: (+ (swoopDroid x?) 5) (+ (swoopDroid y?) 40)
				)
				(swoopDroid dispose:)
			)
			((or (== prevRoomNum 90) (== prevRoomNum 94)) (self posn: shadowDroidX shadowDroidY))
			(else
				(switch (ego loop?)
					(0
						(self
							posn:
								(if (>= shadowDroidX 0)
									(- shadowDroidX 320)
								else
									shadowDroidX
								)
								shadowDroidY
						)
					)
					(1
						(self
							posn:
								(if (<= shadowDroidX 320)
									(+ shadowDroidX 320)
								else
									shadowDroidX
								)
								shadowDroidY
						)
					)
					(2
						(self
							ignoreHorizon: TRUE
							posn:
								shadowDroidX
								(if (>= shadowDroidY 0)
									(- shadowDroidY 190)
								else
									shadowDroidY
								)
						)
					)
					(3
						(self
							posn:
								shadowDroidX
								(if (<= shadowDroidY 190)
									(+ shadowDroidY 190)
								else
									shadowDroidY
								)
						)
					)
				)
			)
		)
	)
)

(instance securityDroid of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			setPri:
				(if
				(and (not (== curRoomNum 93)) (< (ego y?) 116))
					14
				else
					15
				)
			view: 256
			setLoop: 1
			doit:
		)
	)
	
	(method (doit)
		(= shadowDroidX (shadowDroid x?))
		(= shadowDroidY (shadowDroid y?))
		(self
			posn: shadowDroidX (- shadowDroidY 40)
			setCel: (shadowDroid cel?)
		)
	)
)

(instance swoopDroid of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self setPri: 15 view: 256 setLoop: 0 ignoreActors: TRUE)
	)
	
	(method (doit)
		(if (not local2) (return))
		(if (and (<= 0 local3) (<= local3 62))
			(if (> local2 0)
				(self
					setCel: [droidPath local3]
					x: [droidPath (++ local3)]
					y: [droidPath (++ local3)]
				)
				(++ local3)
			else
				(self
					y: [droidPath local3]
					x: (- 320 [droidPath (-- local3)])
					setCel: [droidPath (-- local3)]
				)
				(-- local3)
			)
		else
			(= local2 0)
			(droidScript cue:)
		)
	)
)

(instance zapper of Prop
	(properties)
	
	(method (init)
		(super init:)
		(shadowDroid posn: (ego x?) (ego y?))
		(self
			setPri: (ego priority?)
			view: 256
			posn: (+ (shadowDroid x?) 1) (- (shadowDroid y?) 20)
			setLoop: 3
			setCycle: Forward
			ignoreActors: TRUE
		)
	)
	
	(method (doit)
		(super doit:)
		(if (ego has: 12)
			(ego
				view: (if cel 113 else 92)
				setLoop: (if cel 2 else 5)
				setCel: 0
			)
		else
			(ego view: (if cel 0 else 92) setLoop: 2 setCel: 0)
		)
	)
)

(instance droidScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local2 1)
				(= local3 0)
				(music number: 46 loop: 1 play:)
				(swoopDroid init:)
			)
			(1
				(music number: 47 loop: -1 play:)
				(shadowDroid init:)
				(securityDroid init:)
				(= local1 6)
				(self cue:)
			)
			(2
				(if
					(and
						(-- local1)
						(not (ego mover?))
						(== shadowDroidX 166)
						(== shadowDroidY 97)
					)
					(-- state)
					(Timer setReal: self 2)
				else
					(self cue:)
				)
			)
			(3
				(shadowDroid setMotion: Chase ego 0 self)
			)
			(4
				(if
					(or
						(& (ego onControl: -1) $0002)
						(>= (ego y?) 191)
						(>= (ego x?) 320)
					)
					(= state (- state 2))
				else
					(HandsOff)
				)
				(Timer setCycle: self 1)
			)
			(5
				(zapper init:)
				(jelloSound play:)
				(Timer setCycle: self 4)
			)
			(6
				(if (!= (jelloSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 4)
				else
					(zapper dispose:)
					(jelloSound stop:)
					(Timer setCycle: self 1)
				)
			)
			(7
				(ego
					view: 92
					setLoop: (if (ego has: 12) 5 else 2)
					setCycle: Forward
					cycleSpeed: 2
				)
				(Timer setCycle: self 1)
			)
			(8
				(shadowDroid
					xStep: 6
					yStep: 4
					setMotion: MoveTo (- [droidPath 61] 3) (+ [droidPath 62] 40) self
				)
			)
			(9
				(securityDroid dispose:)
				(shadowDroid dispose:)
				(music number: 46 loop: 1 play:)
				(= local2 -1)
				(= local3 62)
				(swoopDroid init:)
			)
			(10
				(swoopDroid dispose:)
				(if (not (== curRoomNum 93))
					(music number: 102 loop: -1 play:)
				)
				(= scumSoftAlerted 0)
				(Timer setReal: self 6)
			)
			(11
				(music stop:)
				(EgoDead 901 0 7 (if (ego has: iCoveralls) 9 else 15))
			)
		)
	)
)

(instance jelloSound of Sound
	(properties
		number 44
		priority 3
	)
)

(instance vaporizeSound of Sound
	(properties
		number 55
		priority 2
	)
)
