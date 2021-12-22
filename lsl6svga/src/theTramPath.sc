;;; Sierra Script 1.0 - (do not remove this comment)
(script# 825)
(include sci.sh)
(use Main)
(use fileScr)
(use RegionPath)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	theTramPath 0
	tram 1
	tramSoundFx 2
	tramBase 3
)

(local
	[local0 61] = [32767 680 90 128 335 128 32767 640 -25 128 335 128 32767 305 -25 128 335 128 32767 300 -25 128 335 128 32767 200 -25 128 335 128 32767 500 -25 128 335 128 32767 505 -25 128 335 128 32767 660 -25 128 330 128 32767 690 -25 128 335 128 32767 820 -25 85 75 85 -32768]
)
(instance theTramPath of RegionPath
	(properties
		endType -1
	)
	
	(method (init)
		(if (Btst 36) (self endType: 0))
		(cond 
			(
				(and
					(== curRoomNum 680)
					(Btst 36)
					(== (tram heading?) 90)
				)
				(= completed 1)
			)
			(
				(and
					(== curRoomNum 820)
					(not (Btst 36))
					(== (tram heading?) 345)
				)
				(= completed 1)
			)
		)
		(self theRegion: theGame)
		(super init: &rest)
	)
	
	(method (nextRoom)
		(super nextRoom:)
	)
	
	(method (at param1)
		(return [local0 param1])
	)
	
	(method (curRoomCheck &tmp clientZ)
		(= clientZ (client z?))
		(if (== currentRoom curRoomNum)
			(cond 
				((and (== curRoomNum 680) (not (Btst 36))) ((ScriptID 825 1) setScript: (ScriptID 826 2)))
				(
				(and (not (Btst 70)) (== curRoomNum 820) (Btst 36))
					(Bset 70)
					((ScriptID 825 1) setScript: (ScriptID 826 3))
				)
			)
			(client
				view: 82
				z: (if (>= clientZ 1000) (- clientZ 1000) else clientZ)
				illegalBits: theOldBits
				setCycle: Walk
				signal: theOldSignal
			)
			(tramSoundFx number: 39 play: setLoop: -1)
			(if
				(ego
					inRect:
						(- ((ScriptID 825 1) brLeft?) 46)
						(- ((ScriptID 825 1) brTop?) 3)
						(+ ((ScriptID 825 1) brRight?) 46)
						(+ ((ScriptID 825 1) brBottom?) 10)
				)
				(cond 
					(
					(and (== curRoomNum 820) (ego inRect: 0 71 24 100)) (ego posn: (ego x?) 76))
					((== curRoomNum 820) (ego posn: (ego x?) 77))
					(else (ego posn: (ego x?) 119))
				)
			)
		else
			(Bclr 70)
			(client
				view: 98
				z: (if (< clientZ 1000) (+ clientZ 1000) else clientZ)
				illegalBits: 0
				setCycle: 0
				ignoreActors: 1
			)
			(if (== global205 1810) (proc79_7))
			(tramSoundFx number: 0 stop:)
		)
	)
)

(instance tram of Actor
	(properties
		noun 1
		modNum 825
		x 68
		y 132
		view 82
		cycleSpeed 8
		moveSpeed 4
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				((OneOf theVerb 4 2)
					(cond 
						(
							(and
								script
								(script script?)
								(OneOf
									(script script?)
									(ScriptID 826 2)
									(ScriptID 826 3)
								)
							)
							(messager say: 1 4 4 0 0 825)
						)
						((Btst 35) (curRoom setScript: (ScriptID 826 1)))
						(
							(or
								(and (== curRoomNum 820) (Btst 36))
								(and (== curRoomNum 680) (not (Btst 36)))
							)
							(messager say: 1 4 4 0 0 825)
						)
						(else (curRoom setScript: (ScriptID 826 0) 0 theVerb))
					)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance tramBase of Code
	(properties)
	
	(method (doit &tmp tramBrBottom tramBrLeft tramBrTop tramBrRight)
		(BaseSetter tram)
		(= tramBrBottom (tram brBottom?))
		(= tramBrTop (tram brTop?))
		(= tramBrLeft (tram brLeft?))
		(= tramBrRight (tram brRight?))
		(tram
			brBottom: (+ tramBrBottom 10)
			brLeft: (+ tramBrLeft 3)
			brRight: (- tramBrRight 3)
			brTop: (- tramBrTop 5)
		)
	)
)

(instance tramSoundFx of Sound
	(properties
		flags $0001
		number 39
	)
)
