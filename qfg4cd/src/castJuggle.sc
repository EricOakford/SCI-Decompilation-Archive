;;; Sierra Script 1.0 - (do not remove this comment)
(script# 62)
(include sci.sh)
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	castJuggle 0
)

(local
	local0
	newProp
	local2
)
(procedure (localproc_001a &tmp temp0 temp1)
	(= temp1 -32768)
	(= temp0 0)
	(while (<= temp0 10)
		(if (& disabledIcons temp1) (theIconBar disable: temp0))
		(= temp1 (>> temp1 $0001))
		(++ temp0)
	)
)

(instance castJuggle of Script
	(properties)
	
	(method (dispose)
		(Bset 6)
		(theGame handsOn:)
		(localproc_001a)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(self setScript: faceEgo self)
				else
					(ego setMotion: 0)
					(Face ego (+ (ego x?) 5) (+ (ego y?) 5))
					(= cycles (+ (ego cycleSpeed?) 5))
				)
			)
			(1
				(theMusic3 number: 944 setLoop: 1 play:)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(ego view: 19 loop: 5 cel: 0 setCycle: End self)
				else
					(ego view: 15 loop: 0 setCycle: End self)
				)
			)
			(2
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(self cue:)
				else
					(ego setCycle: CT 4 -1 self)
				)
			)
			(3
				((= newProp (Prop new:))
					signal: 24577
					view: 99
					loop: 0
					cel: 0
					x: (ego x?)
					y: (ego y?)
					z: (/ (ego scaleY?) 2)
					setPri: (+ (ego priority?) 1)
					init:
					setScale:
					scaleX: (ego scaleX?)
					scaleY: (ego scaleY?)
					setCycle: Fwd
				)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(self cue:)
				else
					(ego setCycle: CT 2 -1 self)
				)
			)
			(4
				(Bclr 6)
				(cond 
					(
						(or
							(and (< 2800 Clock) (< Clock 3600))
							(and (<= 0 Clock) (<= Clock 771))
						)
						(PalVary pvREVERSE 4)
					)
					((and (< 2600 Clock) (< Clock 2801)) (PalVary pvREVERSE 4))
					((and (< 770 Clock) (< Clock 871)) (PalVary pvREVERSE 4))
				)
				(= seconds 9)
			)
			(5
				(newProp dispose:)
				(ego setCycle: CT 0 -1 self)
			)
			(6
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(ego view: 20 loop: 2 cel: 4)
				else
					(ego normalize: 4)
				)
				(CyclePalette)
				(= ticks 3)
			)
			(7 (self dispose:))
		)
	)
)

(instance faceEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (ego loop?) 2)
					(if (== (ego loop?) 6)
						(ego loop: 2 cel: 4)
					else
						(ego loop: (- (ego loop?) 1))
					)
				else
					(self dispose:)
				)
				(= cycles (ego cycleSpeed?))
			)
			(1 (self init: client caller))
		)
	)
)
