;;; Sierra Script 1.0 - (do not remove this comment)
(script# MAP)
(include game.sh)
(use Main)
(use Procs)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	ShowMap 0
)

(local
	newList
	theGPicNumber =  -1
	[local2 2]
	curRoomLookStr
	local5
	local6 = [
		110 53 49
		120 109 47
		130 162 46
		140 191 47
		150 273 46
		160 48 67
		170 102 66
		180 147 70
		190 206 61
		200 261 66
		210 52 93
		220 139 103
		230 165 84
		235 180 78
		240 211 94
		250 270 96
		260 61 123
		270 104 120
		280 171 117
		290 204 114
		300 273 116
		310 62 149
		320 107 150
		330 155 150
		340 202 138
		350 272 149
		]
)
(procedure (ShowMap &tmp curRoomPicture newEvent temp2 temp3)
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(User canInput: TRUE)
	(theIconBar disable:)
	(theGame setCursor: 69)
	(ego setMotion: 0)
	(= curRoomPicture (curRoom picture?))
	(= curRoomLookStr (curRoom lookStr?))
	(curRoom lookStr: 0)
	(= theGPicNumber overlays)
	(= newList (List new:))
	(= temp3 0)
	(while (< temp3 (cast size?))
		(if (& ((cast at: temp3) signal?) $0080)
			(newList add: (cast at: temp3))
			(cast delete: (cast at: temp3))
		)
		(++ temp3)
	)
	(cast eachElementDo: #hide)
	(curRoom drawPic: 400 10)
	(DrawIconScroll)
	(= local5 0)
	(while (and (<= local5 77) (!= curRoomNum [local6 local5]))
		(+= local5 3)
	)
	(mapMarker
		posn: [local6 (+ local5 1)] [local6 (+ local5 2)]
		setCycle: Forward
	)
	(mapSet add: mapMarker)
	(= tickOffset (- gameTime (GetTime)))
	(repeat
		(= temp2 ((= newEvent (Event new:)) type?))
		(newEvent dispose:)
		(= gameTime (+ tickOffset (GetTime)))
		(Animate (mapSet elements?) 1)
		(breakif (OneOf temp2 4 1 256))
	)
	(HandsOn)
	(NormalEgo 5 egoView)
	(mapSet
		eachElementDo: #dispose
		eachElementDo: #delete
		dispose:
	)
	(curRoom drawPic: curRoomPicture 10)
	(DrawIconScroll)
	(if (!= (= overlays theGPicNumber) -1)
		(DrawPic overlays FADEOUT FALSE currentPalette)
	)
	(cast eachElementDo: #show)
	(= temp3 0)
	(while (< temp3 (newList size?))
		(cast addToEnd: (newList at: temp3))
		(++ temp3)
	)
	(newList release: dispose:)
	(curRoom lookStr: curRoomLookStr)
	(HandsOn 1)
	(theIconBar enable:)
	(DisposeScript 401)
)

(instance mapSet of Set)

(instance mapMarker of Prop
	(properties
		view 810
	)
)
