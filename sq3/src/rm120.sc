;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include game.sh)
(use Main)
(use Intrface)
(use Follow)
(use Chase)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm120 0
)

(local
	local0
	local1
	canEgoFight =  TRUE
	local3
	local4
	local5
	toX
	toY
	userPrevDir
	local9
	local10 = [1 0 3 2]
	bgHits
	egoHits
	local16
	local17
	local18 =  3
	local19
)
(procedure (Face actor1 actor2)
	(DirLoop actor1
		(GetAngle
			(actor1 x?)
			(actor1 y?)
			(actor2 x?)
			(actor2 y?)
		)
	)
)

(procedure (localproc_1f80 param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5)
	(= temp1 (+ (= temp0 (- (param2 x?) 10)) 19))
	(= temp3 (+ (= temp2 (- (param2 y?) 20)) 20))
	(switch (param1 loop?)
		(0
			(= temp4 (+ (param1 x?) 37))
			(= temp5 (- (param1 y?) 13))
		)
		(1
			(= temp4 (- (param1 x?) 38))
			(= temp5 (- (param1 y?) 13))
		)
		(2
			(= temp4 (- (param1 x?) 8))
			(= temp5 (- (param1 y?) 10))
		)
		(3
			(= temp4 (+ (param1 x?) 6))
			(= temp5 (- (param1 y?) 10))
		)
	)
	(return
		(if
			(and
				(<= temp0 temp4)
				(<= temp4 temp1)
				(<= temp2 temp5)
				(<= temp5 temp3)
			)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (localproc_2066 param1 &tmp retVal i)
	(= retVal 1000)
	(for ((= i 0) (= retVal 1000)) (< i argc) ((++ i))
		(if (< [param1 i] retVal) (= retVal [param1 i]))
	)
	(return retVal)
)

(procedure (NoAttack)
	(= canEgoFight FALSE)
	(User canControl: FALSE)
)

(procedure (CanAttack)
	(= canEgoFight TRUE)
	(User canControl: TRUE)
)

(procedure (localproc_20b2)
	(ego
		view: 200
		setMotion: 0
		setLoop: -1
		setCel: -1
		setCycle: Walk
	)
	(legs view: 201 setLoop: -1 setCycle: 0)
)

(instance rm120 of Room
	(properties
		picture 120
		picAngle 80
	)
	
	(method (init &tmp [temp0 50])
		(User canInput: FALSE canControl: TRUE)
		(Load VIEW 195)
		(Load VIEW 196)
		(Load VIEW 198)
		(Load VIEW 200)
		(Load VIEW 201)
		(Load VIEW 202)
		(Load VIEW 203)
		(Load VIEW 204)
		(Load VIEW 205)
		(Load VIEW 206)
		(Load VIEW 207)
		(Load VIEW 208)
		(Load VIEW 209)
		(Load VIEW 211)
		(Load SOUND 18)
		(Load SOUND 19)
		(Load SOUND 33)
		(super init:)
		(ego
			view: 200
			loop: 0
			setLoop: -1
			posn: 62 85
			setStep: 3 1
			illegalBits: cWHITE
			baseSetter: newBase
			ignoreActors: 0
			init:
		)
		(badGuy init:)
		(bgLegs init:)
		(legs init:)
		(Scott init:)
		(Mark init: setMotion: Follow Scott 10)
		(egoPower init:)
		(bgPower init:)
		(= inCartoon FALSE)
		(self setScript: StartScript)
	)
	
	(method (doit &tmp [temp0 50])
		(super doit:)
		(if (and (badGuy isBlocked:) (not egoHits))
			(BadGuy changeState: 15)
		)
		(if (and (ego inRect: 0 0 152 86) (not egoHits))
			(ScottScript changeState: 10)
		)
		(if (and (ego inRect: 0 87 152 189) (not egoHits))
			(ScottScript changeState: 7)
		)
		(if (and (ego inRect: 153 0 319 86) (not egoHits))
			(ScottScript changeState: 4)
		)
		(if (and (ego inRect: 153 87 319 189) (not egoHits))
			(ScottScript changeState: 1)
		)
		(if global219
			(-- local18)
		)
		(if (not local18)
			(= local18 3)
			(= local16 1)
			(= local17 1)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(cond 
					(
						(and
							(or
								(== (event message?) `J)
								(== (event message?) `J)
							)
							canEgoFight
						)
						(self setScript: Punch)
					)
					(
						(and
							(or
								(== (event message?) `M)
								(== (event message?) `M)
							)
							canEgoFight
						)
						(self setScript: EgoBlock)
					)
					(
						(and
							(or
								(== (event message?) `N)
								(== (event message?) `N)
							)
							canEgoFight
						)
						(cast showSelf:)
					)
				)
			)
		)
	)
)

(instance StartScript of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(NoAttack)
				(= egoHits 1)
				(balloon init:)
				(= seconds 3)
			)
			(1
				(balloon dispose:)
				(= egoHits 0)
				(CanAttack)
				(client setScript: 0)
			)
		)
	)
)

(instance Punch of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(= userPrevDir (User prevDir?))
				(NoAttack)
				(if (ego mover?)
					(= toX ((ego mover?) x?))
					(= toY ((ego mover?) y?))
				else
					(= toX 0)
					(= toY 0)
				)
				(ego view: 202 setCel: 0 setMotion: 0)
				(if (and (not local5) (not local4))
					(BadGuy changeState: 7)
				)
				(ego setCycle: EndLoop self)
			)
			(1
				(= local16 4)
				(if
					(and
						(localproc_1f80 ego badGuy)
						(!= (ego loop?) (badGuy loop?))
					)
					(if (not local3)
						(clang1 play:)
						(= local9 1)
						(= local16 -8)
						(= local17 4)
						(BadGuy changeState: 12)
					else
						(clang2 play:)
						(= global176 0)
						(= local16 4)
					)
				)
				(= cycles 2)
			)
			(2
				(localproc_20b2)
				(if (or toX toY)
					(ego setMotion: MoveTo toX toY)
				)
				(= local1 0)
				(CanAttack)
				(User prevDir: userPrevDir)
				(client setScript: 0)
			)
		)
	)
)

(instance EgoBump of Script
	(method (doit)
		(if (and (ego isBlocked:) local1 (not bgHits))
			(self cue:)
		)
		(if (== (legs view?) 208)
			(legs
				setLoop: (ego loop?)
				setPri: (ego priority?)
				x: (ego x?)
				y: (- (ego y?) 1)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp [temp0 50] temp50 temp51)
		(switch (= state newState)
			(0
				(NoAttack)
				(if (== (ego loop?) (badGuy loop?))
					(switch (ego loop?)
						(0 (= temp50 20) (= temp51 0))
						(1 (= temp50 -20) (= temp51 0))
						(2 (= temp50 0) (= temp51 10))
						(3 (= temp50 0) (= temp51 -10))
					)
				else
					(switch (ego loop?)
						(0 (= temp50 -20) (= temp51 0))
						(1 (= temp50 20) (= temp51 0))
						(2 (= temp50 0) (= temp51 -10))
						(3 (= temp50 0) (= temp51 10))
					)
					(legs view: 208 setCycle: Forward)
					(ego
						view: 198
						signal: (& (ego signal?) $fbff)
						setLoop:
						cel: 0
						setCycle: 0
					)
				)
				(ego
					setMotion: MoveTo (+ (ego x?) temp50) (+ (ego y?) temp51) self
				)
				(= local1 1)
			)
			(1
				(localproc_20b2)
				(= local1 0)
				(CanAttack)
				(self dispose:)
			)
		)
	)
)

(instance EgoBlock of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(= userPrevDir (User prevDir?))
				(NoAttack)
				(= local0 1)
				(if (ego mover?)
					(= toX ((ego mover?) x?))
					(= toY ((ego mover?) y?))
				else
					(= toX 0)
					(= toY 0)
				)
				(ego view: 209 setCel: 0 setMotion: 0)
				(RedrawCast)
				(ego setCycle: EndLoop self)
			)
			(1 (= cycles 5))
			(2 (ego setCycle: BegLoop self))
			(3
				(localproc_20b2)
				(if (or toX toY)
					(ego setMotion: MoveTo toX toY)
				)
				(= local0 0)
				(= local16 2)
				(CanAttack)
				(User prevDir: userPrevDir)
				(client setScript: 0)
			)
		)
	)
)

(instance BadGuy of Script
	(method (doit)
		(if (== (bgLegs view?) 206)
			(bgLegs
				setLoop: (badGuy loop?)
				setPri: (badGuy priority?)
				x: (badGuy x?)
				y: (- (badGuy y?) 1)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp [temp0 50] temp50 temp51)
		(switch (= state newState)
			(0
				(cond 
					((< (badGuy loop?) 2)
						(if (> (badGuy y?) (ego y?))
							(badGuy posn: (badGuy x?) (- (badGuy y?) 1))
						else
							(badGuy posn: (badGuy x?) (+ (badGuy y?) 1))
						)
					)
					((> (badGuy x?) (ego x?)) (badGuy posn: (- (badGuy x?) 1) (badGuy y?)))
					(else (badGuy posn: (+ (badGuy x?) 1) (badGuy y?)))
				)
				(badGuy setMotion: Chase ego 45 self)
			)
			(1
				(= cycles 1)
			)
			(2
				(switch (Random 1 2)
					(1 (self changeState: 3))
					(2 (self changeState: 12))
				)
			)
			(3
				(Face badGuy ego)
				(= local5 1)
				(badGuy
					view: 205
					setCel: 0
					setMotion: 0
					signal: (& (badGuy signal?) $fbff)
				)
				(= cycles 2)
			)
			(4
				(badGuy setCycle: EndLoop self)
			)
			(5
				(= local17 4)
				(if (and (localproc_1f80 badGuy ego) (not local1))
					(if (not local0)
						(clang1 play:)
						(= local17 -8)
						(= local16 4)
						(curRoom setScript: EgoBump)
					else
						(clang2 play:)
					)
				)
				(= cycles 2)
			)
			(6
				(= local5 0)
				(if (< (Random 1 100) 21)
					(self changeState: 3)
				else
					(self changeState: 12)
				)
			)
			(7
				(if
					(or
						(!= (badGuy loop?) [local10 (ego loop?)])
						(>
							(GetDistance
								(ego x?)
								(ego y?)
								(badGuy x?)
								(badGuy y?)
							)
							55
						)
					)
					(self changeState: 15)
				else
					(= cycles (Random 2 7))
				)
			)
			(8
				(= local3 1)
				(Face badGuy ego)
				(badGuy view: 207 setCel: 0 setMotion: 0)
				(RedrawCast)
				(badGuy setCycle: EndLoop self)
			)
			(9 (= cycles 5))
			(10 (badGuy setCycle: BegLoop self))
			(11
				(= local3 0)
				(= local17 2)
				(self changeState: 12)
			)
			(12
				(= local4 1)
				(bgLegs view: 206 setCycle: Forward)
				(badGuy
					view: (if local9 198 else 203)
					setLoop: (badGuy loop?)
					setCel: (if local9 1 else -1)
					setCycle: (if local9 0 else Walk)
				)
				(self changeState: 13)
			)
			(13
				(switch (badGuy loop?)
					(0 (= temp50 -20) (= temp51 0))
					(1 (= temp50 20) (= temp51 0))
					(2 (= temp50 0) (= temp51 -10))
					(3 (= temp50 0) (= temp51 10))
				)
				(badGuy
					setMotion: MoveTo (+ (badGuy x?) temp50) (+ (badGuy y?) temp51) self
				)
			)
			(14
				(= local9 0)
				(self changeState: 15)
			)
			(15
				(bgLegs view: 204 setCycle: 0)
				(badGuy
					view: 203
					setLoop: -1
					setCel: -1
					setCycle: Walk
					setMotion: 0
				)
				(= local5 0)
				(= local4 0)
				(= local9 0)
				(self changeState: 0)
			)
			(16 (badGuy setScript: 0))
		)
	)
)

(instance ScottScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(if (Scott inRect: 153 87 319 189)
					(Scott setMotion: MoveTo 228 70 self)
				else
					(self changeState: 2)
				)
			)
			(2
				(if (and (!= (Scott x?) 82) (!= (Scott y?) 72))
					(Scott setMotion: MoveTo 82 72 self)
				)
			)
			(3 (Face Scott ego))
			(4
				(if (Scott inRect: 153 0 319 86)
					(Scott setMotion: MoveTo 230 105 self)
				else
					(self changeState: 5)
				)
			)
			(5
				(if (and (!= (Scott x?) 83) (!= (Scott y?) 103))
					(Scott setMotion: MoveTo 83 103 self)
				)
			)
			(6 (Face Scott ego))
			(7
				(if (Scott inRect: 0 87 152 189)
					(Scott setMotion: MoveTo 82 72 self)
				else
					(self changeState: 8)
				)
			)
			(8
				(if (and (!= (Scott x?) 228) (!= (Scott y?) 70))
					(Scott setMotion: MoveTo 228 70 self)
				)
			)
			(9 (Face Scott ego))
			(10
				(if (Scott inRect: 0 0 152 86)
					(Scott setMotion: MoveTo 228 70 self)
				else
					(self changeState: 11)
				)
			)
			(11
				(if
				(and (!= (Scott x?) 230) (!= (Scott y?) 105))
					(Scott setMotion: MoveTo 230 105 self)
				)
			)
			(12 (Face Scott ego))
			(13
				(Scott setMotion: MoveTo 153 105 self)
			)
			(14 (Face Scott ego))
		)
	)
)

(instance EgoDies of Script ;EO: Was "EgoDead"; renamed to avoid compiler confusion with the procedure
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(badGuy setMotion: 0 setCycle: 0)
				(ego illegalBits: 0 setMotion: 0)
				(RedrawCast)
				(ego
					view: 211
					setLoop: (if (> (ego x?) 153) 0 else 1)
					setPri: (ego priority?)
					y: (+ (ego y?) 32)
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(legs dispose:)
			)
			(1
				(boom play:)
				(ShakeScreen 10 3)
				(Print 120 0 #at -1 130 #width 280)
				(EgoDead 0 0 9 13)
			)
		)
	)
)

(instance BgDead of Script
	(properties)
	
	(method (doit)
		(legs cel: (ego cel?) loop: (ego loop?))
		(if (not (-- local18))
			(= local18 (Random 1 3))
			(if (badGuy mover?) (badGuy setLoop: (Random 0 3)))
		)
		(bgLegs
			setLoop: (badGuy loop?)
			setPri: (badGuy priority?)
			x: (badGuy x?)
			y: (- (badGuy y?) 1)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp [temp0 50] temp50 temp51 temp52 temp53 temp54 temp55 badGuyX badGuyY)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local18 2)
				(badGuy ignoreActors: 1 illegalBits: 0)
				(bgLegs ignoreActors: 1)
				(localproc_20b2)
				(ego ignoreActors: 1)
				(legs ignoreActors: 1)
				(if (> (ego x?) 160)
					(ego setMotion: MoveTo 130 90)
				else
					(ego setMotion: MoveTo 170 90)
				)
				(= badGuyX (badGuy x?))
				(= badGuyY (badGuy y?))
				(= temp50 (GetDistance badGuyX badGuyY 80 78))
				(= temp51 (GetDistance badGuyX badGuyY 98 73))
				(= temp52 (GetDistance badGuyX badGuyY 128 68))
				(= temp53 (GetDistance badGuyX badGuyY 187 68))
				(= temp54 (GetDistance badGuyX badGuyY 213 73))
				(= temp55 (GetDistance badGuyX badGuyY 241 78))
				(bgLegs view: 206 setCycle: Forward)
				(badGuy view: 198 setCel: 1 setCycle: 0)
				(switch (localproc_2066 temp50 temp51 temp52 temp53 temp54 temp55)
					(temp50
						(badGuy setMotion: MoveTo 80 78 self)
					)
					(temp51
						(badGuy setMotion: MoveTo 98 73 self)
					)
					(temp52
						(badGuy setMotion: MoveTo 128 68 self)
					)
					(temp53
						(badGuy setMotion: MoveTo 187 68 self)
					)
					(temp54
						(badGuy setMotion: MoveTo 213 73 self)
					)
					(temp55
						(badGuy setMotion: MoveTo 241 78 self)
					)
				)
			)
			(1
				(bgLegs dispose:)
				(if (<= (ego y?) (+ (badGuy y?) 6))
					(= temp50 1)
				else
					(= temp50 -1)
				)
				(badGuy
					view: 211
					illegalBits: 0
					setLoop: (if (> (badGuy x?) 150) 2 else 3)
					setPri: (+ (ego priority?) temp50)
					y: (+ (badGuy y?) 32)
					cycleSpeed: 1
					setMotion: 0
					setCycle: EndLoop self
				)
				(if (== (badGuy loop?) 2)
					(= local19 1)
				else
					(= local19 -1)
				)
			)
			(2
				(boom play:)
				(ShakeScreen 10 3)
				(theGame changeScore: 100)
				(Scott illegalBits: 0 setPri: (+ (badGuy priority?) 1))
				(Mark setPri: (Scott priority?))
				(Scott
					setMotion:
						MoveTo
						(+ (badGuy x?) (* local19 40))
						(- (badGuy y?) 32)
						self
				)
			)
			(3
				(balloon
					init:
					setLoop: 2
					setCel: (if (< (Scott x?) 160) 0 else 1)
				)
				(= seconds 3)
			)
			(4
				(balloon dispose:)
				(RedrawCast)
				(Scott dispose:)
				(Mark dispose:)
				(= cycles 2)
			)
			(5
				(ego setCel: 0 setLoop: 2)
				(legs setLoop: 2 posn: (ego x?) (- (ego y?) 1))
				(guy
					init:
					setLoop: 0
					posn: (ego x?) (- (ego y?) 16)
					setPri: (+ (ego priority?) 2)
					setMotion: MoveTo (- (ego x?) 5) (+ (ego y?) 3) self
				)
				(square init: stopUpd:)
			)
			(6
				(guy
					setLoop: 1
					setCel: 0
					setCycle: 0
					setMotion: MoveTo (- (ego x?) 5) (+ (ego y?) 30) self
				)
			)
			(7
				(guy view: 68 setLoop: -1 setStep: 6 3 setCycle: Walk)
				(guy
					setMotion: MoveTo (+ (guy x?) (* local19 12)) (guy y?) self
				)
			)
			(8
				(if (< (guy y?) (badGuy y?))
					(guy
						setPri: (- (ego priority?) 1)
						setMotion:
							MoveTo
							(- (badGuy x?) (* local19 5))
							(+ (badGuy y?) 10)
							self
					)
				else
					(= cycles 2)
				)
			)
			(9
				(guy
					setMotion:
						MoveTo
						(+ (badGuy x?) (* local19 20))
						(+ (badGuy y?) 10)
						self
				)
			)
			(10
				(guy
					setMotion: MoveTo (+ (badGuy x?) (* local19 40)) (badGuy y?) self
				)
			)
			(11
				(guy dispose:)
				(theMusic stop:)
				(= cycles 2)
			)
			(12 (curRoom newRoom: 94))
		)
	)
)

(instance legs of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 201
			setLoop: (ego loop?)
			setCel: (ego cel?)
			posn: (ego x?) (- (ego y?) 1)
			setPri: (ego priority?)
			ignoreActors: 1
		)
	)
	
	(method (doit)
		(super doit:)
		(if (and (not (ego mover?)) (== canEgoFight 1))
			(= local16 0)
		)
		(if (and (ego mover?) (!= (self view?) 208))
			(self
				setLoop: (ego loop?)
				setCel: (ego cel?)
				setPri: (ego priority?)
				x: (ego x?)
				y: (- (ego y?) 1)
			)
		)
	)
)

(instance bgLegs of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 204
			setLoop: (badGuy loop?)
			setCel: (badGuy cel?)
			posn: (badGuy x?) (- (badGuy y?) 1)
			setPri: (badGuy priority?)
			ignoreActors: 1
		)
	)
	
	(method (doit)
		(super doit:)
		(if (and (badGuy mover?) (!= (self view?) 206))
			(self
				setLoop: (badGuy loop?)
				setCel: (badGuy cel?)
				setPri: (badGuy priority?)
				x: (badGuy x?)
				y: (- (badGuy y?) 1)
			)
		)
	)
)

(instance badGuy of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 203
			loop: 1
			posn: 272 86
			setCycle: Walk
			setStep: 3 1
			illegalBits: cWHITE
			setScript: BadGuy
			ignoreActors: FALSE
			baseSetter: newBase
		)
	)
)

(instance Scott of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 195
			loop: 0
			posn: 159 79
			setCycle: Walk
			setStep: 6 3
			illegalBits: cWHITE
			ignoreActors: TRUE
			setScript: ScottScript
		)
	)
)

(instance Mark of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 196
			loop: 0
			posn: 162 82
			setCycle: Walk
			setStep: 6 3
			illegalBits: cWHITE
			ignoreActors: TRUE
		)
	)
)

(instance guy of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 97
			loop: 0
			setCycle: Walk
			setStep: 3 3
			illegalBits: 0
			ignoreActors: TRUE
		)
	)
)

(instance egoPower of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 211
			setLoop: 4
			setCel: 0
			posn: 78 12
			setPri: 14
			setCycle: 0
			ignoreActors: 1
		)
	)
	
	(method (doit)
		(if (< (self x?) 32)
			(self setCycle: Forward)
		else
			(self setCycle: 0 setCel: 0)
		)
		(if local16
			(if (> (- (self x?) local16) 78)
				(self x: 78)
			else
				(self x: (- (self x?) local16))
			)
			(= local16 0)
		)
		(if (and (<= (self x?) 11) (not egoHits))
			(= bgHits 1)
			(NoAttack)
			(curRoom setScript: EgoDies)
			(self dispose:)
		)
		(super doit:)
	)
)

(instance bgPower of Prop
	(method (init)
		(super init:)
		(self
			view: 211
			setLoop: 4
			setCel: 0
			posn: 230 12
			setPri: 14
			setCycle: 0
			ignoreActors: TRUE
		)
	)
	
	(method (doit)
		(if (> (self x?) 273)
			(self setCycle: Forward)
		else
			(self setCycle: 0 setCel: 0)
		)
		(if local17
			(if (< (+ (self x?) local17) 230)
				(self x: 230)
			else
				(self x: (+ (self x?) local17))
			)
			(= local17 0)
		)
		(if (and (>= (self x?) 296) (not bgHits))
			(= egoHits 1)
			(NoAttack)
			(BadGuy changeState: 16)
			(ScottScript changeState: 13)
			(curRoom setScript: BgDead)
			(self dispose:)
		)
		(super doit:)
	)
)

(instance square of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 117
			setLoop: 1
			setCel: 1
			posn: (ego x?) (- (ego y?) 18)
			setPri: (+ (ego priority?) 1)
			setCycle: 0
			ignoreActors: 1
		)
	)
)

(instance balloon of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 117
			setLoop: 3
			setCel: 0
			posn: (Scott x?) (+ (Scott y?) 11)
			setPri: 15
			setCycle: 0
			ignoreActors: 1
		)
	)
)

(instance clang1 of Sound
	(properties
		number 18
		priority 2
	)
)

(instance clang2 of Sound
	(properties
		number 19
		priority 2
	)
)

(instance boom of Sound
	(properties
		number 33
		priority 3
	)
)

(instance newBase of Code
	(properties)
	
	(method (doit param1)
		(param1 brTop: (- (param1 y?) 4))
		(param1 brBottom: (+ (param1 y?) 4))
		(param1 brLeft: (- (param1 x?) 20))
		(param1 brRight: (+ (param1 x?) 20))
	)
)
