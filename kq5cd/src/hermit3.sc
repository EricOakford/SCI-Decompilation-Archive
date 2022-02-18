;;; Sierra Script 1.0 - (do not remove this comment)
(script# 112)
(include sci.sh)
(use Main)
(use KQ5Room)
(use Sync)
(use RandCyc)
(use Motion)
(use Actor)
(use System)

(public
	hermit3 0
)

(instance hermit3 of KQ5Room
	(properties
		picture 88
		style $000a
	)
	
	(method (init)
		(super init:)
		(Load rsSCRIPT 941)
		(if (!= (theMusic number?) 820)
			(theMusic number: 820 loop: -1 vol: 127 playBed:)
		)
		(if (== gGNumber_2 46)
			(sailBoat
				init:
				y: (if (== curRoomNum 44) 153 else 173)
				stopUpd:
				ignoreActors:
			)
			(sail
				posn: (+ (sailBoat x?) 7) (sailBoat y?)
				setPri: (sailBoat priority?)
				init:
			)
		)
		(egoMouth init:)
		(hermitMouth init:)
		(hermit_eyes init:)
		(chimney setCycle: Forward init:)
		(self setScript: cartoon)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(super dispose:)
	)
)

(instance cartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(hermitMouth cycleSpeed: 2)
				(hermit_eyes cycleSpeed: 20 setCycle: RandCycle)
				(hermitMouth setCycle: MouthSync 1142)
				(SpeakAudio 1142 self)
			)
			(2
				(cls)
				(hermitMouth setCycle: 0)
				(egoMouth cycleSpeed: 2)
				(egoMouth setCycle: MouthSync 1143)
				(SpeakAudio 1143 self)
			)
			(3
				(egoMouth setCycle: 0)
				(cls)
				(hermitMouth setCycle: MouthSync 1144)
				(SpeakAudio 1144 self)
			)
			(4
				(hermitMouth setCycle: 0)
				(cls)
				(= seconds 1)
			)
			(5
				(hermitMouth setCycle: MouthSync 1145)
				(SpeakAudio 1145 self)
			)
			(6
				(hermitMouth setCycle: 0)
				(cls)
				(= seconds 2)
			)
			(7
				(hermitMouth setCycle: MouthSync 1146)
				(SpeakAudio 1146 self)
			)
			(8
				(hermitMouth setCycle: 0)
				(if (Btst 55)
					(theMusic fade:)
					(if (!= gGNumber_2 46)
						(curRoom newRoom: 46)
					else
						(curRoom newRoom: 110)
					)
				else
					(curRoom newRoom: 111)
				)
			)
		)
	)
)

(instance egoMouth of Prop
	(properties
		x 183
		y 85
		view 827
	)
)

(instance hermitMouth of Prop
	(properties
		x 135
		y 104
		view 827
		loop 2
	)
)

(instance hermit_eyes of Prop
	(properties
		x 144
		y 83
		view 827
		loop 3
	)
)

(instance chimney of Prop
	(properties
		x 18
		y 70
		z 20
		view 625
		loop 2
		cycleSpeed 3
		detailLevel 3
	)
)

(instance sailBoat of Actor
	(properties
		x 280
		y 173
		view 618
		cel 1
		priority -1
		signal $4001
	)
)

(instance sail of Actor
	(properties
		z 15
		view 618
		loop 2
		signal $4001
	)
)
