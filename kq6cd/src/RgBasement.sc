;;; Sierra Script 1.0 - (do not remove this comment)
(script# 81)
(include sci.sh)
(use Main)
(use CastleRoom)
(use RegionPath_918)
(use System)

(public
	RgBasement 0
	guardPath1 1
	guardPath2 2
)

(local
	[local0 27] = [32767 840 16 52 79 52 100 114 131 134 178 134 192 113 152 113 32767 720 334 166 108 142 112 134 142 135 -32768]
	[local27 37] = [32767 720 142 135 112 134 108 142 -27 158 32767 710 164 114 126 115 125 182 343 182 32767 840 -17 182 145 182 167 134 131 134 100 114 79 52 6 52 -32768]
)
(class RgBasement of rgCastle
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		rFlag1 0
		rFlag2 0
		rFlag3 0
		dungeonEntered 0
		lastSeconds 0
		loiterTimer -1
		weddingRemind 0
		guardTimer 0
		guard2Timer 0
		guard1Code 0
		guard2Code 0
		stopTimers 0
		weddingMusicCount -1
	)
	
	(method (init)
		(super init: &rest)
		(if (self tstFlag: 709 1)
			((ScriptID 80 5)
				view: 725
				regPathID: guardPath1
				setMotion: guardPath1 (ScriptID 80 5) (ScriptID 80 5)
				init:
			)
		)
		(if (self tstFlag: 709 2)
			((ScriptID 80 6)
				view: 727
				regPathID: guardPath2
				setMotion: guardPath2 (ScriptID 80 6) (ScriptID 80 6)
				init:
			)
		)
		(if
			(and
				(or
					(not (OneOf prevRoomNum 840 710 720 770 820 780))
					(!= (theMusic number?) 704)
				)
				(not ((ScriptID 80 0) tstFlag: 709 2))
				(not ((ScriptID 80 0) tstFlag: 709 8192))
			)
			(theMusic fadeTo: 704 -1)
		)
	)
	
	(method (doit)
		(if
			(and
				(== loiterTimer -1)
				(not (curRoom script?))
				(not (self tstFlag: 709 1))
				(not (self tstFlag: 709 2))
			)
			(= loiterTimer 36)
		)
		(super doit:)
	)
	
	(method (dispose)
		((ScriptID 80 5) z: 0 setMotion: 0)
		((ScriptID 80 6) z: 0 setMotion: 0)
		(super dispose: &rest)
		(DisposeScript 918)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 840 710 720 770 820 780))
		(= initialized 0)
		(super newRoom: n &rest)
	)
	
	(method (doLoiter &tmp temp0)
		(if
			(and
				(not (& rFlag1 $0004))
				(not (OneOf curRoomNum 820 780))
			)
			(if
			(and (not (& rFlag1 $0001)) (not (& rFlag1 $0002)))
				(switch curRoomNum
					(710
						(= rFlag1 (| rFlag1 $0002))
					)
					(else 
						(= rFlag1 (| rFlag1 $0001))
					)
				)
				(= loiterTimer 1)
				(curRoom doLoiter:)
			else
				(self startGuard:)
			)
		)
	)
	
	(method (setupGuards)
		(if (and (!= curRoomNum 840) (& rFlag1 $0001))
			((ScriptID 80 5)
				okToCheck:
					(if
					(>= 10 (/ (((ScriptID 80 5) regPathID?) value?) 2))
						(>= (/ (((ScriptID 80 5) regPathID?) value?) 2) 4)
					else
						0
					)
			)
		)
		(if (and (!= curRoomNum 840) (& rFlag1 $0002))
			((ScriptID 80 6)
				okToCheck:
					(if
					(>= 13 (/ (((ScriptID 80 6) regPathID?) value?) 2))
						(>= (/ (((ScriptID 80 6) regPathID?) value?) 2) 3)
					else
						0
					)
			)
		)
		(super setupGuards:)
	)
	
	(method (warnUser param1 param2 param3 param4)
		(switch param1
			(1
				(if (and (> argc 3) param4)
					(messager say: param2 param3 param4)
				)
				(cond 
					((>= ((ScriptID 80 0) weddingMusicCount?) 3) (= rFlag1 (| rFlag1 $0004)) (self startGuard:))
					((not ((ScriptID 80 0) weddingRemind?)) ((ScriptID 80 0) weddingRemind: 15))
				)
			)
		)
	)
	
	(method (startGuard)
		(if
		(and (& rFlag1 $0001) (not ((ScriptID 80 5) mover?)))
			((ScriptID 80 5)
				view: 725
				init:
				regPathID: guardPath1
				setMotion: guardPath1 (ScriptID 80 5) (ScriptID 80 5) 1
			)
		)
		(if
		(and (& rFlag1 $0002) (not ((ScriptID 80 6) mover?)))
			((ScriptID 80 6)
				view: 727
				init:
				regPathID: guardPath2
				setMotion: guardPath2 (ScriptID 80 6) (ScriptID 80 6) 1
			)
		)
		(self setupGuards:)
	)
	
	(method (resetGuard param1 param2)
		(= rFlag1 (& rFlag1 (~ param2)))
		(if (IsObject (param1 regPathID?))
			((param1 regPathID?) value: 0)
		)
	)
)

(instance guardPath1 of RegionPath
	(properties
		endType 0
		theRegion 81
	)
	
	(method (init)
		(if (RgBasement tstFlag: 709 4) (= endType 2))
		(super init: &rest)
	)
	
	(method (nextRoom &tmp temp0)
		(= temp0 (== currentRoom curRoomNum))
		(super nextRoom: &rest)
		(if (and (IsObject curRoom) (not temp0))
			(cond 
				((> value 2) (curRoom warnUser: 5 currentRoom))
				((not ((ScriptID 81 0) tstFlag: 709 4)) (curRoom warnUser: 6 7))
			)
			(if (== currentRoom curRoomNum)
				(soundFx2 number: 702 loop: 1 play:)
			)
		)
	)
	
	(method (at param1)
		(return [local0 param1])
	)
)

(instance guardPath2 of RegionPath
	(properties
		endType 0
		theRegion 81
	)
	
	(method (nextRoom &tmp temp0)
		(= temp0 (== currentRoom curRoomNum))
		(if
			(and
				(< value 2)
				(== curRoomNum 840)
				(not (RgBasement tstFlag: 709 8))
			)
			(RgBasement setFlag: 709 8)
			(= value 10)
		)
		(super nextRoom: &rest)
		(if (and (IsObject curRoom) (not temp0))
			(if (> value 2)
				(curRoom warnUser: 5 currentRoom)
			else
				(curRoom warnUser: 6 8)
			)
			(if (== currentRoom curRoomNum)
				(soundFx2 number: 702 loop: 1 play:)
			)
		)
	)
	
	(method (at param1)
		(return [local27 param1])
	)
)
