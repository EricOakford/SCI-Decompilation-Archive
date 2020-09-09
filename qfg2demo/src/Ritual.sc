;;; Sierra Script 1.0 - (do not remove this comment)
(script# RITUAL) ;11
(include game.sh)
(use Main)
(use Flame)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRitual 0
)

(local
	local0
	local1
	candleXY = [108 124 94 146 160 158 223 146 206 126 160 117 -1]
)
(instance demoRitual of Room
	(properties
		picture rRitualChamber
		style IRISIN
	)
	
	(method (init &tmp i theX theY)
		(LoadMany VIEW rRitualChamber vAdAvisRitual vThark vEgoCastLightning)
		(super init:)
		(ego view: vEgo loop: 0 cel: 0 posn: 197 173 init:)
		(adAvis init:)
		(adAvisHead init:)
		(tharkTorso init:)
		(tharkLegs init:)
		(= i 0)
		(while (!= [candleXY (* i 2)] -1)
			(= theX [candleXY (+ (* i 2) 0)])
			(= theY [candleXY (+ (* i 2) 1)])
			((PicView new:)
				view: rRitualChamber
				loop: 2
				x: theX
				y: theY
				init:
			)
			(if (!= i 0)
				((Flame new:)
					view: rRitualChamber
					loop: 1
					x: theX
					y: theY
					z: 15
					init:
				)
			)
			(++ i)
		)
		(brazier init:)
		(addToPics doit:)
		(self setScript: rmScript)
	)
)

(instance adAvis of Prop
	(properties
		x 150
		y 109
		view vAdAvisRitual
	)
)

(instance adAvisHead of Prop
	(properties
		x 148
		y 68
		view vAdAvisRitual
		loop 1
		cel 2
	)
)

(instance shieldSpell of Prop
	(properties
		x 152
		y 86
		view vAdAvisRitual
	)
)

(instance tharkTorso of Prop
	(properties
		x 276
		y 127
		view vThark
		cycleSpeed 2
	)
)

(instance tharkLegs of View
	(properties
		x 272
		y 163
		view vThark
		loop 1
	)
)

(instance brazier of Flame
	(properties
		x 114
		y 117
		view rRitualChamber
	)
)

(instance lightning of Prop
	(properties
		view vEgoCastLightning
		loop 1
	)
	
	(method (init)
		(super init:)
		(self posn: (- (ego x?) 2) (- (ego y?) 48))
	)
)

(instance rmScript of Script
	
	(method (doit)
		(if (> local1 state)
			(if (or seconds cycles)
				(= seconds (= cycles 0))
			)
			(self cue:)
		else
			(super doit:)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(Print RITUAL 0
					#at -1 12
					#dispose
				)
				(tharkTorso setLoop: 0 setCycle: Forward)
				(= seconds 5)
			)
			(1
				(++ local0)
				(tharkTorso
					posn: (- (tharkTorso x?) 2) (- (tharkTorso y?) 1)
				)
				(tharkLegs setCel: (+ (tharkLegs cel?) 1))
				(= cycles 1)
			)
			(2
				(if (< local0 4)
					(self changeState: 1)
				else
					(tharkTorso x: (- (tharkTorso x?) 2))
					(tharkLegs setCel: (+ (tharkLegs cel?) 1))
					(= cycles 1)
				)
			)
			(3
				(ego
					setMotion: 0
					view: 40
					setLoop: 0
					cel: 0
					setCycle: CycleTo 1 1 self
				)
			)
			(4
				(ego setCycle: CycleTo (- (ego lastCel:) 1) 1)
				(= seconds 5)
			)
			(5
				(self setScript: adAvisCastSpell)
				(ego cel: (ego lastCel:))
				(lightning
					init:
					setCycle: CycleTo (- (lightning lastCel:) 2) 1 self
				)
			)
			(6
				(lightning setCycle: EndLoop)
				(ego loop: 2 cel: 0 setCycle: EndLoop)
				(globalSound number: sHardBattleEnd loop: 1 play: self)
			)
			(7
				(cls)
				(curRoom newRoom: IBLIS)
			)
		)
	)
)

(instance adAvisCastSpell of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(adAvis setCycle: EndLoop self)
			)
			(1
				(adAvisHead loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(shieldSpell
					loop: 4
					setPri: (+ (adAvis priority?) 1)
					init:
					setCycle: EndLoop self
				)
			)
			(3
				(shieldSpell loop: 3 setCycle: Forward)
				(adAvis setCycle: BegLoop)
			)
		)
	)
)
