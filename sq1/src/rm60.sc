;;; Sierra Script 1.0 - (do not remove this comment)
(script# 60)
(include game.sh)
(use Main)
(use Intrface)
(use Deltaur)
(use SQRoom)
(use Elevator)
(use RangeOsc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Sound)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm60 0
)

(local
	local0
	[upPts 12] = [0 0 319 0 319 50 154 50 103 81 0 77]
	[upPts2 12] = [0 86 162 86 203 59 319 59 319 189 0 189]
	[lpPts 32] = [0 0 319 0 319 173 261 173 270 167 250 167 234 173 137 173 137 167 151 163 151 96 116 96 116 164 129 167 129 173 0 174]
	[lpPts2 8] = [319 185 319 189 0 189 0 184]
	toX
	toY
)
(instance rm60 of SQRoom
	(properties
		lookStr {This is an interesting place. Strange devices decorate almost every area.}
		picture 60
		east 64
		west 59
	)
	
	(method (init)
		(self setRegions: DELTAUR)
		(HandsOff)
		(LoadMany 132 6 339)
		(LoadMany 128 160 328)
		(features
			add: balls hb mosl mdofl fllym flum hole
			eachElementDo: #init
			doit:
		)
		(iris init: stopUpd:)
		(up points: @upPts size: 6)
		(up2 points: @upPts2 size: 6)
		(lp points: @lpPts size: 16)
		(lp2 points: @lpPts2 size: 4)
		(if (or (== prevRoomNum 66) (< prevRoomNum 10))
			(= currentFloor 2)
		)
		(switch prevRoomNum
			(east
				(= style WIPELEFT)
				(ego x: 315)
				(if (== currentFloor 2) (ego y: 178))
			)
			(west (= style SCROLLLEFT) (ego x: 10))
			(else 
				(= currentFloor 2)
				(= style -32767)
			)
		)
		(if (== currentFloor 2) (LoadMany VIEW 80))
		(ego init:)
		(super init:)
		(addToPics add: robot eachElementDo: #init doit:)
		(robot approachVerbs: verbDo verbLook verbTaste verbSmell)
		(DeltaurRegion eDoor1: e1Door)
		(e1Door
			view: 160
			locked: 0
			loop: 0
			posn: 133 171
			description: { elevator door}
			lookStr: {This is an elevator.}
			whereTo: 66
			level: 2
			setPri: 13
			exiting: (if (== prevRoomNum 66) else (< prevRoomNum 10))
			polyCode: 0
			init:
		)
		(switch currentFloor
			(1 (self addObstacle: up up2))
			(2 (self addObstacle: lp lp2))
		)
		(pe1 setCycle: Forward init:)
		(pe2 setCycle: Forward init:)
		(beam setCycle: Forward setScript: switchit init:)
		(lamp setCycle: Forward init:)
	)
	
	(method (doit)
		(cond 
			(script 0)
			(
				(and
					(> (theGame detailLevel:) 1)
					(not (beam script?))
				)
				(beam setScript: switchit)
			)
			(
			(and (<= (theGame detailLevel:) 1) (beam script?))
				(pe1 show:)
				(pe2 show:)
				(beam show:)
				(lamp show:)
				(beam setScript: 0)
			)
			((e1Door inFront:) (e1Door open:))
			((& (ego onControl: 0) $0002) (self setScript: toastEgo))
		)
		(super doit: &rest)
	)
	
	(method (newRoom n)
		(ego setCycle: 0 setPri: -1)
		(super newRoom: n)
	)
	
	(method (notify)
		(if (== prevRoomNum 66) (HandsOff))
	)
)

(instance e1Door of Elevator
	(properties
		description {elevator door}
		sightAngle 90
		priority 2
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (!= currentFloor level)
			(Print 60 0)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance up of Polygon
	(properties
		type PBarredAccess
	)
)

(instance up2 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance lp of Polygon
	(properties
		type PBarredAccess
	)
)

(instance lp2 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance iris of Prop
	(properties
		x 234
		y 12
		description {garbage disposal}
		lookStr {This utilitarian-looking door probably leads to the trash disposal facility.}
		view 160
		loop 1
		cycleSpeed 6
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(switch theVerb
				(verbDo
					(ego setScript: shreadEgo)
				)
				(verbSmell
					(Print 60 1)
				)
				(verbTaste
					(Print 60 2)
				)
				(verbTalk
					(Print 60 3)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print 60 4)
		)
	)
)

(instance pe1 of Prop
	(properties
		x 215
		y 121
		description {purple energy}
		approachX 251
		approachY 168
		lookStr {Vast amounts of energy.}
		view 160
		loop 2
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbDo verbTaste verbSmell)
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 2)
			(if (and (== theVerb verbUse) (== theItem iPulseray))
				(curRoom setScript: shipBlowsUp)
			else
				(super doVerb: theVerb theItem &rest)
			)
		else
			(Print 60 0)
		)
	)
)

(instance pe2 of Prop
	(properties
		x 304
		y 142
		description {purple energy}
		approachX 251
		approachY 168
		lookStr {Vast amounts of energy.}
		view 160
		loop 3
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbDo verbTaste verbSmell)
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 2)
			(if (and (== theVerb verbUse) (== theItem iPulseray))
				(curRoom setScript: shipBlowsUp)
			else
				(super doVerb: theVerb theItem &rest)
			)
		else
			(Print 60 0)
		)
	)
)

(instance beam of Prop
	(properties
		x 266
		y 134
		description {beam}
		approachX 251
		approachY 168
		lookStr {An intense beam of energy.}
		view 160
		loop 4
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbDo verbTaste verbSmell)
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 2)
			(if (and (== theVerb verbUse) (== theItem iPulseray))
				(curRoom setScript: shipBlowsUp)
			else
				(super doVerb: theVerb theItem &rest)
			)
		else
			(Print 60 0)
		)
	)
)

(instance lamp of Prop
	(properties
		x 64
		y 37
		description {lamp}
		lookStr {bottled energy}
		view 160
		loop 5
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(switch theVerb
				(verbDo
					(Print 60 5)
				)
				(verbSmell
					(Print 60 6)
				)
				(verbTaste
					(Print 60 7)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print 60 0)
		)
	)
)

(instance switchit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(pe1 show:)
				(pe2 show:)
				(beam hide:)
				(= ticks (Random 18 180))
			)
			(1
				(beamSound number: 6 loop: -1 play:)
				(beam show:)
				(= ticks (Random 60 240))
			)
			(2
				(beamSound stop:)
				(beam hide:)
				(= ticks (Random 18 60))
			)
			(3
				(= state (- state 4))
				(pe1 hide:)
				(pe2 hide:)
				(= ticks (Random 18 180))
			)
		)
	)
)

(instance shreadEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 249 41 self)
			)
			(1
				(HandsOff)
				(ego view: 160 loop: 7 cel: 0)
				(iris startUpd: setCycle: EndLoop self)
			)
			(2 (ego setCycle: EndLoop self))
			(3
				(ego x: 259 y: 35 loop: 8 cel: 0 setCycle: EndLoop self)
			)
			(4
				(EgoDead 160 8 0 60 8)
				(self dispose:)
			)
		)
	)
)

(instance toastEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (OneOf (ego loop?) 0 4 6)
					(= register 1)
				else
					(= register 0)
				)
				(if (== (DeltaurRegion egoStatus?) egoWithHelmet)
					(ego setLoop: 2)
				else
					(ego setLoop: 0)
				)
				(= toY (+ (ego y?) 10))
				(if register
					(= toX (- (ego x?) 30))
				else
					(= toX (+ (ego x?) 30))
				)
				(ego
					loop: (+ (ego loop?) register)
					view: 80
					cel: 0
					cycleSpeed: 3
					setCycle: CycleTo 13 1 self
					setMotion: JumpTo toX toY
				)
			)
			(1
				(ego cycleSpeed: 10 setCycle: RangeOsc 5 11 13 self)
			)
			(2
				(EgoDead 80 0 5 60 9)
				(self dispose:)
			)
		)
	)
)

(instance shipBlowsUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr 53)
				(= ticks 18)
				(DeltaurRegion script: 0)
				(ego setScript: (ScriptID 703 22) self)
			)
			(1
				(pe1 dispose:)
				(pe2 dispose:)
				(lamp dispose:)
				(beam
					view: 328
					loop: 0
					cel: 0
					cycleSpeed: 27
					setCycle: 0
					setPri: 13
					setScript: 0
					posn: 247 169
					setCycle: EndLoop self
				)
			)
			(2
				(ShakeScreen 3 7)
				(theMusic number: 339 loop: 1 play: self)
			)
			(3
				(iris dispose:)
				((ScriptID DELTAUR 16) dispose:)
				((ScriptID DELTAUR 17) dispose:)
				(ego hide:)
				(e1Door dispose:)
				(curRoom drawPic: 50 IRISOUT)
				(beam
					view: 328
					loop: 0
					cel: 0
					cycleSpeed: 36
					setCycle: 0
					setPri: 1
					setScript: 0
					posn: 186 70
				)
				(= ticks 60)
			)
			(4
				(ShakeScreen shakeSDiagonal 7)
				(theMusic number: 339 loop: 1 play: self)
			)
			(5
				(beam posn: 188 60 setCycle: CycleTo 1 1 self)
			)
			(6
				(beam posn: 192 55 setCycle: EndLoop self)
			)
			(7
				(EgoDead 945 0 0 60 10)
				(self dispose:)
			)
		)
	)
)

(instance balls of RegionFeature
	(properties
		x 253
		y 132
		description {electric balls}
		onMeCheck $4000
		lookStr {From the looks of them, these globes contain high-power Wally coils, each one capable of discharging jillions of volts of deadly electricity.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbSmell
				(Print 60 11)
			)
			(verbTaste
				(Print 60 12)
			)
			(verbTalk
				(Print 60 13)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mosl of RegionFeature
	(properties
		x 176
		y 21
		description {blue machine}
		onMeCheck $2000
		level 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbSmell
				(Print 60 14)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hb of RegionFeature
	(properties
		x 63
		y 21
		description {bottled energy}
		onMeCheck cLRED
		lookStr {The egg-shaped globe pulses with blue-white light. It appears to be some exotic form of bottled energy, or maybe a 60,000 watt light bulb.}
		level 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 60 5)
			)
			(verbSmell
				(Print 60 6)
			)
			(verbTaste
				(Print 60 7)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mdofl of RegionFeature
	(properties
		x 68
		y 137
		description {rounded purple thing}
		onMeCheck $0800
		lookStr {Looks like a great place to curl up and take a nap. Too bad you're in such a hurry.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 60 15)
			)
			(verbTaste
				(Print 60 16)
			)
			(verbTalk
				(Print 60 17)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fllym of RegionFeature
	(properties
		description {gold machine}
		onMeCheck $0400
		lookStr {It's an expensive-looking machine that goes `ping.'}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 60 18)
			)
			(verbSmell
				(Print 60 19)
			)
			(verbTaste
				(Print 60 20)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance flum of RegionFeature
	(properties
		x 11
		y 63
		description {blue speaker}
		onMeCheck $0040
		lookStr {It does sort of look like a speaker, doesn't it. But it isn't.}
		level 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbTalk
				(Print 60 21)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hole of RegionFeature
	(properties
		x 219
		y 158
		description {hole}
		onMeCheck $0200
		lookStr {Your engineering expertise informs you that this is a carelessly discarded Williamson coil.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 60 22)
				(Print 60 23)
			)
			(verbDo
				(Print 60 24)
			)
			(verbSmell
				(Print 60 25)
			)
			(verbTaste
				(Print 60 2)
			)
			(verbTalk
				(Print 60 26)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance robot of PicView
	(properties
		x 266
		y 48
		description {cleaning droid}
		approachX 250
		approachY 53
		lookStr {Broom at the ready, the cleaning droid waits for something useful to do.}
		view 160
		loop 6
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(switch theVerb
				(verbDo
					(Print 60 27)
				)
				(verbSmell
					(Print 60 28)
				)
				(verbTaste
					(Print 60 29)
				)
				(verbTalk
					(Print 60 30)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print 60 0)
		)
	)
)

(instance beamSound of Sound
	(properties
		number 6
	)
)
