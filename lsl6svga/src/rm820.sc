;;; Sierra Script 1.0 - (do not remove this comment)
(script# 820)
(include sci.sh)
(use Main)
(use fileScr)
(use EgoDead)
(use OccCyc)
(use LarryRoom)
(use CycleBet)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm820 0
	rideTram 1
	takeABreak 2
	curtis 3
)

(local
	local0
	local1
	local2
)
(instance rm820 of LarryRoom
	(properties
		noun 1
		picture 820
		horizon 40
		north 230
		south 800
		west 690
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						75
						0
						90
						153
						95
						178
						90
						192
						99
						208
						139
						218
						139
						197
						94
						292
						88
						277
						71
						151
						75
						121
						67
						87
						39
						80
						39
						88
						51
						100
						73
						90
						77
					yourself:
				)
		)
		(super init: &rest)
		(theMusic stop:)
		(theMusic2
			number: 820
			play:
			setVol: 50
			setLoop: -1
			fade: 127 10 10 0
		)
		(cardSlot init: ignoreActors: 1 approachVerbs: 4 10)
		(frontFlowers init:)
		(stairs init:)
		(otherStairs init:)
		(leftTree init:)
		(rightTree init:)
		(backFlowers init:)
		(if (and (!= prevRoomNum 830) (Btst 23))
			(= local1 1)
			(curtis
				x: 133
				y: 93
				view: 824
				loop: 1
				cel: 0
				init:
				ignoreActors: 1
			)
			(thatOldTram
				init:
				ignoreActors: 1
				setScript: tramFiddling
			)
			(curRoom
				addObstacle:
					(= local0
						((Polygon new:)
							type: 2
							init: 39 83 107 82 152 84 152 92 40 89
							yourself:
						)
					)
			)
		)
		(switch prevRoomNum
			(230
				(self setScript: enterFromPool)
			)
			(690
				(if (Btst 35)
					((ScriptID 825 1) z: 0 view: 90 posn: 0 90 init:)
					(self setScript: rideTram)
				else
					(self setScript: enterFromHall)
				)
			)
			(840
				(self setScript: enterFromCamp)
			)
			(830
				(thatOldTram x: 75 y: 85 init:)
				(ego posn: 136 91 init: view: 85 loop: 2 setCel: 4)
				(self setScript: fromTramCU)
			)
			(else 
				(self setScript: enterFromExterior)
			)
		)
	)
	
	(method (doit &tmp egoEdgeHit)
		(= egoEdgeHit (ego edgeHit?))
		(cond 
			(script)
			((== egoEdgeHit 1)
				(if (ego has: 14)
					(curtisTimer dispose:)
					(curRoom setScript: gimmeMyLight)
				else
					(curRoom newRoom: 230)
				)
			)
			((== egoEdgeHit 3)
				(if (ego has: 14)
					(curtisTimer dispose:)
					(curRoom setScript: gimmeMyLight)
				else
					(curRoom setScript: exitSouth)
				)
			)
			((== egoEdgeHit 4)
				(if (ego has: 14)
					(curtisTimer dispose:)
					(curRoom setScript: gimmeMyLight)
				else
					(curRoom newRoom: 690)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if (ego has: 14) (Bclr 23))
		(theMusic2 fade:)
		(DisposeScript -572)
		(super dispose:)
	)
	
	(method (newRoom n)
		(if (!= n 830) (Bclr 53) (Bclr 71))
		(super newRoom: n)
	)
)

(instance gimmeMyLight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 6 2 7 10 self)
			)
			(1
				(theGame handsOff:)
				(ego setMotion: PolyPath 82 82 self)
			)
			(2 (ego setHeading: 180 self))
			(3
				(messager sayRange: 6 2 7 11 14 self)
			)
			(4 (messager say: 5 0 4 0 self))
			(5
				(ego put: 14)
				((curRoom obstacles?) delete: local0)
				(local0 dispose:)
				(thatOldTram dispose:)
				(Bclr 23)
				((ScriptID 825 1) init: setMotion: (ScriptID 825 0))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (ego x?) 200 self)
			)
			(1 (curRoom newRoom: 800))
		)
	)
)

(instance exitWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo -20 (ego y?) self)
			)
			(1 (curRoom newRoom: 690))
		)
	)
)

(instance helpCurtis of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (thatOldTram script?)
					((thatOldTram script?) dispose:)
				)
				(proc79_3 ego curtis self)
			)
			(1 (= cycles 2))
			(2
				(messager sayRange: 6 2 7 1 4 self)
			)
			(3
				(curtis setCycle: 0)
				(ego setMotion: PolyPath 121 91 self)
			)
			(4
				(if (cast contains: tramLid) (tramLid dispose:))
				(curtis hide:)
				(ego
					view: 85
					posn: 136 91
					loop: 0
					setCel: 0
					setSpeed: 12
					setCycle: End self
				)
			)
			(5
				(messager sayRange: 6 2 7 5 7 self)
			)
			(6 (= seconds 5))
			(7 (messager say: 6 2 7 8 self))
			(8
				(ego get: 14 posn: 121 91 normalize: 900 3)
				(theGame changeScore: 2 267)
				(curtis
					show:
					view: 824
					loop: 1
					setPri: 80
					setCel: 3
					setCycle: Beg self
				)
			)
			(9
				(curtis
					loop: 0
					setCel: 4
					cycleSpeed: 12
					setCycle: Beg self
				)
			)
			(10
				(theGame handsOn:)
				(curtis
					view: 81
					setCycle: Walk
					cycleSpeed: 6
					setStep: 4 2
					setMotion: MoveTo 75 84 self
				)
			)
			(11
				(curtis dispose:)
				(thatOldTram
					view: 83
					loop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(12
				(thatOldTram loop: 2 setCel: 0 setCycle: End self)
			)
			(13
				(messager say: 6 2 7 9 self)
			)
			(14
				(curtisTimer setReal: curtis 15)
				(self dispose:)
			)
		)
	)
)

(instance curtisNTheTram of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curtis x: 327 y: 82 init: ignoreActors: 1)
				(cardSlot setCycle: End self)
				(sFx number: 821 loop: 1 play:)
			)
			(1
				(theGame handsOn:)
				(curtis
					setCycle: Walk
					setStep: 6 2
					cycleSpeed: 9
					moveSpeed: 9
					setMotion: MoveTo 274 84 self
				)
			)
			(2
				(sFx number: 821 loop: 1 play:)
				(cardSlot setCycle: Beg)
				(curtis setMotion: MoveTo 132 84 self)
			)
			(3
				(curtis ignoreActors: 1 setMotion: MoveTo 74 84 self)
			)
			(4
				(curtis hide:)
				(thatOldTram
					view: 83
					loop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(5
				(sFx number: 572 loop: 1 play:)
				(= ticks 30)
			)
			(6
				(thatOldTram loop: 2 setCel: 0 setCycle: End self)
			)
			(7
				(sFx number: 572 loop: 1 play:)
				(= ticks 33)
			)
			(8
				(thatOldTram setCycle: Beg self)
			)
			(9
				(sFx number: 572 loop: 1 play:)
				(= ticks 40)
			)
			(10
				(thatOldTram setCycle: End self)
			)
			(11
				(sFx number: 572 loop: 1 play:)
				(= ticks 33)
			)
			(12
				(thatOldTram setCycle: Beg self)
			)
			(13
				(sFx number: 572 loop: 1 play:)
				(= ticks 60)
			)
			(14
				(thatOldTram setCycle: End self)
			)
			(15
				(sFx number: 572 loop: 1 play:)
				(= ticks 40)
			)
			(16
				(thatOldTram setCycle: Beg self)
			)
			(17
				(messager say: 0 0 12 1 self)
			)
			(18 (= ticks 20))
			(19
				(thatOldTram loop: 0 setCel: 6 setCycle: Beg self)
			)
			(20
				(thatOldTram view: 80 ignoreActors: 1)
				(curtis
					show:
					setCycle: Walk
					setPri: 84
					setStep: 4 2
					ignoreActors: 1
					setMotion: MoveTo 133 93 self
				)
			)
			(21
				(messager say: 0 0 12 2 self)
			)
			(22
				(curRoom
					addObstacle:
						(= local0
							((Polygon new:)
								type: 2
								init: 39 83 107 82 152 84 152 92 40 89
								yourself:
							)
						)
				)
				(curtis view: 824 loop: 0 setCel: 0 setCycle: End self)
				(= local1 1)
			)
			(23 (= seconds 2))
			(24
				(messager say: 0 0 12 3 self)
			)
			(25
				(curtis loop: 1 setCel: 0 setCycle: End self)
			)
			(26 (= seconds 2))
			(27
				(curtis loop: 2 setCel: 0 setCycle: End self)
			)
			(28 (= seconds 2))
			(29
				(curtis loop: 3 setCel: 0 setCycle: End self)
			)
			(30
				(tramLid init: ignoreActors: 1 setCel: 0)
				(curtis loop: 4 setCel: 0)
				(= ticks 180)
			)
			(31
				(curtis setCycle: CT 3 1 self)
				(tramLid setCycle: End)
			)
			(32
				(if (not local2)
					(messager say: 0 0 12 4 self)
				else
					(= local2 1)
					(self cue:)
				)
			)
			(33 (= ticks 120))
			(34
				(tramLid dispose:)
				(curtis setCycle: End self)
			)
			(35 (= ticks 30))
			(36
				(= state (- state 8))
				(self cue:)
			)
			(37
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance tramFiddling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(curtis loop: 3 setCel: 0 setCycle: End self)
			)
			(2
				(tramLid init: ignoreActors: 1 setCel: 0)
				(curtis loop: 4 setCel: 0)
				(= ticks 180)
			)
			(3
				(curtis setCycle: CT 3 1 self)
				(tramLid setCycle: End)
			)
			(4 (= ticks 120))
			(5
				(tramLid dispose:)
				(curtis setCycle: End self)
			)
			(6
				(sFx number: 345 loop: 1 play:)
				(= ticks 30)
			)
			(7
				(= state (- state 7))
				(self cue:)
			)
		)
	)
)

(instance rideTramOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 825 1) setMotion: MoveTo -25 85 self)
			)
			(1 (curRoom newRoom: 690))
		)
	)
)

(instance rideTram of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 825 2) play: setLoop: -1)
				(if (not (Btst 36))
					(curRoom setScript: rideTramOut)
				else
					(theGame handsOn:)
					(Load rsSCRIPT 826)
					(LoadMany 128 88 82)
					(theIconBar
						disableIcon: (ScriptID 0 3) (ScriptID 0 5) (ScriptID 0 6)
						show:
					)
					((ScriptID 825 1)
						setCycle: Walk
						setMotion: MoveTo 133 85 self
					)
				)
			)
			(1
				(Bset 70)
				(theGame handsOff:)
				(self setScript: (ScriptID 826 1) self)
			)
			(2
				(if (and (not (Btst 160)) (Btst 205)) (Bset 71))
				(self setScript: (ScriptID 826 3) self)
			)
			(3 (= cycles 2))
			(4
				(if (and (not (Btst 160)) (Btst 205))
					(theIconBar
						enableIcon:
							(ScriptID 0 3)
							(ScriptID 0 5)
							(ScriptID 0 6)
							(ScriptID 0 9)
					)
					(Bclr 35)
					(Bclr 36)
					((ScriptID 825 0)
						currentRoom: 820
						value: 58
						endType: -1
						next:
					)
					(ego setScript: takeABreak)
					(self dispose:)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance takeABreak of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 825 2) stop:)
				(Bset 53)
				((ScriptID 825 1) dispose:)
				(thatOldTram view: 80 init:)
				(= ticks 210)
			)
			(1
				(theGame handsOn:)
				(curtis view: 91 setCycle: OccCyc self 1 30 90)
				(= seconds 16)
			)
			(2 (= seconds 16))
			(3
				(theGame handsOff:)
				(curtis
					view: 81
					setCycle: Walk
					cycleSpeed: 6
					ignoreActors: 1
					setStep: 4 2
					setMotion: MoveTo 75 84 self
				)
			)
			(4
				(curtis dispose:)
				(if (cast contains: (ScriptID 825 1))
					((ScriptID 825 1) dispose:)
				)
				(thatOldTram
					view: 83
					init:
					loop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(5
				(thatOldTram loop: 2 setCel: 0 setCycle: End self)
			)
			(6 (messager say: 5 0 4 0 self))
			(7
				(Bclr 71)
				(ego put: 14)
				(thatOldTram dispose:)
				(Bclr 23)
				((ScriptID 825 1) init: setMotion: (ScriptID 825 0))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance goForSmoke of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 152 79 self)
			)
			(1 (proc79_3 ego curtis self))
			(2
				(messager say: 6 42 6 0 self)
			)
			(3
				(theGame changeScore: 4 268)
				(ego put: 25)
				(theGame handsOn:)
				(curtis
					view: 81
					ignoreActors: 1
					setCycle: Walk
					cycleSpeed: 9
					moveSpeed: 9
					xStep: 6
					setMotion: MoveTo 130 82 self
				)
				((ScriptID 825 2) stop:)
				(thatOldTram view: 80 init: loop: 0 setCel: 0)
				(curRoom
					addObstacle:
						(= local0
							((Polygon new:)
								type: 2
								init: 25 83 107 82 124 82 124 91 24 89
								yourself:
							)
						)
				)
			)
			(4
				(theGame handsOn:)
				(curtis ignoreActors: 0 setMotion: MoveTo 279 82 self)
			)
			(5
				(if (> (ego x?) 239)
					(messager say: 0 0 6 0 self)
				else
					(self cue:)
				)
			)
			(6
				(curtis view: 92 setCel: 0 setCycle: End self)
			)
			(7
				(cardSlot setCycle: End self)
				(sFx number: 821 loop: 1 play: setVol: 127)
			)
			(8
				(curtis
					view: 81
					setCycle: Walk
					setMotion: MoveTo 320 82 self
				)
			)
			(9
				(cardSlot setCycle: Beg self)
				(sFx number: 821 loop: 1 play:)
			)
			(10
				(if global205 (proc79_7))
				(curtis dispose:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromHall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: -5 80
					init:
					normalize: 900
					setMotion: MoveTo 4 80 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromPool of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					ignoreHorizon: 1
					posn: 61 33
					init:
					setMotion: DPath 91 51 101 66 self
				)
			)
			(1
				(ego ignoreHorizon: 0 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromCamp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: 330 84
					init:
					normalize: 900 1
					setMotion: MoveTo 271 84 self
				)
				(cardSlot setCel: 6)
			)
			(1
				(cardSlot setCycle: Beg self)
				(sFx number: 821 loop: 1 play:)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromExterior of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: 219 186
					setScale: 0
					init:
					normalize: 900
					setMotion: DPath 213 139 206 121 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 340 105
					init:
					normalize: 900
					setMotion: MoveTo 300 105 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance checkOutTram of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 136 91 self)
			)
			(1
				(if (not (Btst 265))
					(messager say: 5 4 0 0 self)
				else
					(self cue:)
				)
			)
			(2
				(ego
					view: 85
					setSpeed: 12
					loop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(theGame changeScore: 6 265)
				(= seconds 3)
			)
			(4 (curRoom newRoom: 830))
		)
	)
)

(instance fromTramCU of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1
				(ego setSpeed: 12 setCycle: Beg self)
			)
			(2
				(sFx number: 345 loop: 1 play:)
				(ego
					normalize: 900
					ignoreActors: 1
					setMotion: MoveTo 135 78 self
				)
			)
			(3
				(ego ignoreActors: 0 setHeading: 180 self)
			)
			(4 (= cycles 2))
			(5
				(if (Btst 23)
					(messager say: 0 0 7 0 self)
				else
					(messager say: 0 0 11 0 self)
				)
			)
			(6
				(if (Btst 23)
					(thatOldTram setScript: curtisNTheTram)
				else
					(curRoom
						addObstacle:
							(= local0
								((Polygon new:)
									type: 2
									init: 25 83 107 82 124 82 124 91 24 89
									yourself:
								)
							)
					)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance slideCard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 821
					loop: 0
					setCel: 0
					setSpeed: 12
					setCycle: CT 4 1 self
				)
			)
			(1
				(ego setCycle: End self)
				(sFx number: 823 loop: 1 play: setVol: 127)
			)
			(2
				(messager say: 2 10 0 0 self)
			)
			(3
				(ego view: 900 setCycle: Walk)
				(cardSlot cycleSpeed: 12 setCycle: End self)
				(sFx number: 821 loop: 1 play: setVol: 127)
			)
			(4
				(theGame changeScore: 12 269)
				(ego normalize: 900 0 setMotion: MoveTo 330 82 self)
			)
			(5 (= ticks 60))
			(6 (curRoom newRoom: 840))
		)
	)
)

(instance grabFence of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsSOUND 403)
				(theGame handsOff:)
				(ego setMotion: PolyPath 294 82 self)
			)
			(1 (messager say: 3 4 0 1 self))
			(2
				(ego view: 822 loop: 0 setSpeed: 12 setCycle: CT 4 1 self)
			)
			(3
				(sFx number: 403 play: setLoop: -1 setVol: 127)
				(ego setCycle: End self)
			)
			(4
				(ego setCycle: CycleBet 7 8 3 self)
			)
			(5
				(sFx stop:)
				(ego
					loop: 1
					cycleSpeed: 8
					setCel: 0
					setCycle: CT 6 1 self
				)
			)
			(6
				(sFx number: 520 loop: 1 play: setVol: 127)
				(ego setCycle: End self)
			)
			(7 (= seconds 3))
			(8 (EgoDead 19 self))
			(9
				(ego normalize: 900 0)
				(= ticks 75)
			)
			(10
				(messager say: 3 4 0 2 self)
			)
			(11
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance curtis of Actor
	(properties
		noun 6
		x 128
		y 84
		view 81
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(2
					(cond 
						(local1 (curRoom setScript: helpCurtis))
						((== (ego script?) takeABreak) (messager say: 6 2 6) (return 1))
						(else (super doVerb: theVerb))
					)
				)
				(1
					(cond 
						(local1 (messager say: 6 1 7) (return 1))
						((== (thatOldTram script?) curtisNTheTram) (messager say: 6 1 3) (return 1))
						((== (ego script?) takeABreak) (messager say: 6 1 6) (return 1))
						(else (super doVerb: theVerb))
					)
				)
				((OneOf theVerb 42 43)
					(if (== (ego script?) takeABreak)
						(ego setScript: 0)
						(curRoom setScript: goForSmoke)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(curRoom setScript: gimmeMyLight)
	)
)

(instance thatOldTram of Prop
	(properties
		noun 5
		x 75
		y 85
		view 80
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				((OneOf theVerb 4 64)
					(cond 
						((Btst 23) (messager say: 5 4 7) (return 1))
						((cast contains: curtis) (messager say: 5 4 2) (return 1))
						(else (curRoom setScript: checkOutTram))
					)
				)
				(5
					(if (ego has: 14)
						(messager say: 6 5 1)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(24
					(curtisTimer dispose:)
					(curtis cue:)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance cardSlot of Prop
	(properties
		noun 2
		approachX 276
		approachY 82
		x 311
		y 80
		view 820
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(10
					(if
						(or
							(cast contains: thatOldTram)
							(== ((ScriptID 825 0) currentRoom?) 820)
						)
						(messager say: 2 10 2)
						(return 1)
					else
						(curRoom setScript: slideCard)
					)
				)
				(1
					(if
						(and
							(not (cast contains: curtis))
							(cast contains: thatOldTram)
						)
						(messager say: 2 1 9)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(4
					(if
						(or
							(cast contains: thatOldTram)
							(== ((ScriptID 825 0) currentRoom?) 820)
						)
						(messager say: 2 10 2)
						(return 1)
					else
						(curRoom setScript: grabFence)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance tramLid of Prop
	(properties
		x 131
		y 91
		view 824
		loop 5
	)
)

(instance frontFlowers of Feature
	(properties
		noun 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 107 37 116 96 91 144 94 157 103 191 102 204 138 0 139
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance stairs of Feature
	(properties
		noun 4
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 56 30 66 27 86 35 114 51 129 67 84 67 90 59 78 41 56 31
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance otherStairs of Feature
	(properties
		noun 4
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 208 138 186 88 223 87 218 138
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance leftTree of Feature
	(properties
		noun 7
		y 150
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 26 106 14 48 1 48 3 13 49 15 47 47 51 61 60 76 81 97
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rightTree of Feature
	(properties
		noun 9
		y 150
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 151 102 159 36 151 17 200 24 183 56 178 70 180 95
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance backFlowers of Feature
	(properties
		noun 11
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 56 66 50 57 47 33 54 32 77 40 88 57 84 66 57 66
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance sFx of Sound
	(properties)
)

(instance curtisTimer of Timer
	(properties)
)
