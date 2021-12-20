;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include sci.sh)
(use Main)
(use ModalPlane)
(use TextView)
(use PushButton)
(use GenDialog)
(use Polygon)
(use Feature)

(public
	oTravelScreen 0
)

(instance oTravelScreen of ModalPlane
	(properties
		picture 9000
		priority 500
	)
	
	(method (init &tmp temp0)
		(super init: 0 0 639 479)
		((ScriptID 64017 0) set: 0)
		(self setPri: 500 drawPic: picture)
		(oExitButton init: self)
		(voDestination init: self)
		(foBridgeEx init: self)
		(foCaptainSuite init: self)
		(foBoardingDeck init: self)
		(foLounge init: self)
		(foHorseShoeArea init: self)
		(foPoopDeck init: self)
		(foPromenadeDeck init: self)
		(foSwimmingPool init: self)
		(foBoningSuite init: self)
		(foRestaurant init: self)
		(foLobbyUpper init: self)
		(foLobbyLower init: self)
		(foCasinoLobby init: self)
		(foCasino init: self)
		(foBreakRoom init: self)
		(foBowling init: self)
		(foBallroom init: self)
		(foLuvMaster init: self)
		(foCookOff init: self)
		(foLibrary init: self)
		(foBestDressed init: self)
		(foPinSetter init: self)
		(foDewmiCabin init: self)
		(foBeaverHold init: self)
		(foLuggageHold init: self)
		(foKitchen init: self)
		(foLarryRoom init: self)
		(foFocsle init: self)
		(foPurserDesk init: self)
	)
	
	(method (dispose)
		((ScriptID 64017 0) clear: 0)
		(super dispose: &rest)
	)
)

(class TravelFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		isHighlighted 0
	)
	
	(method (init)
		(super init: &rest)
		(self forceCursor: (ScriptID 64006 0))
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(if (!= (event plane?) plane) (event localize: plane))
		(cond 
			((event claimed?) (return 1))
			((and (self onMe: event) (self isNotHidden:))
				(if (not isHighlighted) (self highlight:))
				(if (& (event type?) evMOUSEBUTTON)
					(= temp1 0)
					(CueObj state: 0 cycles: 0 client: self theVerb: temp1)
					(CueObj changeState: 3)
				)
				(return (event claimed: 1))
			)
			(isHighlighted (self highlight: 0))
		)
		(return 0)
	)
	
	(method (doVerb)
		(= isHighlighted 0)
		(if (== newRoomNum curRoomNum) (oTravelScreen dispose:))
	)
	
	(method (highlight param1)
		(if (and argc (not param1))
			(= isHighlighted 0)
			(voDestination draw: (MakeMessageText 0 0 1 2 90))
		else
			(= isHighlighted 1)
			(voDestination
				draw:
					{%s %s}
					(MakeMessageText 0 0 1 1 90)
					(MakeMessageText noun 0 0 1 90)
			)
		)
	)
)

(instance oExitButton of PushButton
	(properties
		x 618
		y 1
		view 9000
	)
	
	(method (doSelect)
		(oTravelScreen dispose:)
	)
)

(instance voDestination of TextView
	(properties
		x 324
		y 460
		fixPriority 1
		font 2510
		fore 0
	)
	
	(method (init)
		(= back skip)
		(= priority (+ (oTravelScreen priority?) 1))
		(super init: &rest)
	)
)

(instance foBridgeEx of TravelFeature
	(properties
		noun 1
		x 508
		y 87
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 167 28 135 38 128 48 145 57 185 62 225 48
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 210)
		(super doVerb:)
	)
)

(instance foCaptainSuite of TravelFeature
	(properties
		noun 2
		x 508
		y 87
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 172 25 227 8 283 28 226 49
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 220)
		(super doVerb:)
	)
)

(instance foFocsle of TravelFeature
	(properties
		noun 4
		x 561
		y 252
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 125 113 143 106 218 145 175 149 128 142 111 130 123 112
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 262)
		(super doVerb:)
	)
)

(instance foPromenadeDeck of TravelFeature
	(properties
		noun 5
		x 508
		y 87
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 204 137 336 93 352 101 219 144
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 260)
		(super doVerb:)
	)
)

(instance foLounge of TravelFeature
	(properties
		noun 6
		x 508
		y 87
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 279 71 336 93 217 131 196 128 165 117 169 109
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 240)
		(super doVerb:)
	)
)

(instance foBoardingDeck of TravelFeature
	(properties
		noun 7
		x 508
		y 87
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 271 64 304 53 396 84 353 100
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 230)
		(super doVerb:)
	)
)

(instance foHorseShoeArea of TravelFeature
	(properties
		noun 15
		x 508
		y 87
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						392
						120
						449
						99
						474
						110
						444
						121
						496
						139
						532
						128
						563
						141
						486
						169
						477
						161
						478
						143
						442
						130
						409
						129
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 250)
		(super doVerb:)
	)
)

(instance foPoopDeck of TravelFeature
	(properties
		noun 16
		x 508
		y 87
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 447 121 477 110 526 127 496 137
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 255)
		(super doVerb:)
	)
)

(instance foSwimmingPool of TravelFeature
	(properties
		noun 8
		x 508
		y 87
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 4 266 46 240 91 222 188 266 123 288 64 298 8 303 1 304
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 301)
		(super doVerb:)
	)
)

(instance foBoningSuite of TravelFeature
	(properties
		noun 9
		x 508
		y 87
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 111 132 128 143 169 149 192 148 192 160 156 161 124 152 112 141
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 311)
		(super doVerb:)
	)
)

(instance foRestaurant of TravelFeature
	(properties
		noun 11
		x 567
		y 62
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 198 191 303 156 395 188 289 225
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 330)
		(super doVerb:)
	)
)

(instance foLobbyUpper of TravelFeature
	(properties
		noun 13
		x 567
		y 62
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						403
						130
						447
						135
						473
						145
						477
						156
						460
						167
						415
						177
						376
						174
						348
						163
						345
						150
						350
						159
						372
						149
						403
						144
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 340)
		(super doVerb:)
	)
)

(instance foLobbyLower of TravelFeature
	(properties
		noun 13
		x 567
		y 62
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						367
						248
						409
						242
						444
						246
						472
						257
						477
						267
						465
						277
						438
						285
						386
						287
						351
						274
						344
						262
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 340)
		(super doVerb:)
	)
)

(instance foCasinoLobby of TravelFeature
	(properties
		noun 30
		x 379
		y 140
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 416 239 445 230 513 253 481 264 469 251 443 244
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 360)
		(super doVerb:)
	)
)

(instance foCasino of TravelFeature
	(properties
		noun 19
		x 432
		y 118
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 433 224 485 208 506 216 529 208 572 224 552 230 579 241 530 259
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 370)
		(super doVerb:)
	)
)

(instance foBreakRoom of TravelFeature
	(properties
		noun 20
		x 534
		y 99
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 487 206 508 199 524 206 509 214
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 34)
			(curRoom newRoom: 380)
		else
			(curRoom newRoom: 381)
		)
		(super doVerb:)
	)
)

(instance foBowling of TravelFeature
	(properties
		noun 22
		x 534
		y 99
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 511 198 550 186 639 217 601 232
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 390)
		(super doVerb:)
	)
)

(instance foBallroom of TravelFeature
	(properties
		noun 17
		x 534
		y 99
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 143 322 236 355 109 396 29 364
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 402)
		(super doVerb:)
	)
)

(instance foLuvMaster of TravelFeature
	(properties
		noun 18
		x 314
		y 233
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 160 318 175 313 213 325 196 331
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 410)
		(super doVerb:)
	)
)

(instance foCookOff of TravelFeature
	(properties
		noun 21
		x 429
		y 184
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 265 318 303 330 286 338 247 324
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 420)
		(super doVerb:)
	)
)

(instance foLibrary of TravelFeature
	(properties
		noun 14
		x 494
		y 156
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 362 130 382 123 399 130 400 141 372 147 369 134
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 430)
		(super doVerb:)
	)
)

(instance foBestDressed of TravelFeature
	(properties
		noun 29
		x 429
		y 184
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 232 293 251 287 286 300 268 307
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 440)
		(super doVerb:)
	)
)

(instance foPinSetter of TravelFeature
	(properties
		noun 26
		x 429
		y 184
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 610 241 614 263 638 255 638 229
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 33)
			(curRoom newRoom: 500)
		else
			(curRoom newRoom: 501)
		)
		(super doVerb:)
	)
)

(instance foDewmiCabin of TravelFeature
	(properties
		noun 24
		x 309
		y 318
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 234 396 249 393 249 412 237 415
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 510)
		(super doVerb:)
	)
)

(instance foBeaverHold of TravelFeature
	(properties
		noun 27
		x 194
		y 409
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 599 274 638 257 638 286 597 302
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 32)
			(curRoom newRoom: 520)
		else
			(curRoom newRoom: 521)
		)
		(super doVerb:)
	)
)

(instance foLuggageHold of TravelFeature
	(properties
		noun 23
		x 194
		y 409
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 191 477 308 439 314 458 254 479
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 31)
			(curRoom newRoom: 530)
		else
			(curRoom newRoom: 531)
		)
		(super doVerb:)
	)
)

(instance foKitchen of TravelFeature
	(properties
		noun 10
		x 469
		y 299
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 122 217 196 193 287 226 216 250
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 540)
		(super doVerb:)
	)
)

(instance foLarryRoom of TravelFeature
	(properties
		noun 25
		x 561
		y 252
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 546 330 556 328 556 345 545 347
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 30)
			(curRoom newRoom: 550)
		else
			(curRoom newRoom: 551)
		)
		(super doVerb:)
	)
)

(instance foPurserDesk of TravelFeature
	(properties
		noun 12
		x 561
		y 252
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 331 140 361 131 368 136 369 147 349 157 346 147
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom newRoom: 341)
		(super doVerb:)
	)
)
