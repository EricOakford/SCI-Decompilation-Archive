;;; Sierra Script 1.0 - (do not remove this comment)
(script# 950)
(include sci.sh)
(use Main)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	vInterfaceView 0
	vEssenceView 1
	pEyelid 2
	vSandPotView 3
	vCrystalPotView 4
	vMetalPotView 5
	vTarPotView 6
	vWoodPotView 7
	vElectricPotView 8
	vAshPotView 9
	vWaterPotView 10
	vFabricPotView 11
	vWaxPotView 12
	sEyeOpen 13
	sEyeClose 14
)

(instance vInterfaceView of View
	(properties
		view 904
	)
	
	(method (init)
		(super init: gNewCast)
		(pEye init:)
		(pEyelid init:)
		(fInventoryWindow init:)
		(fUserControls init:)
		(vLeftCorner init:)
		(vRightCorner init:)
		(vEssenceView init:)
		(if (& global104 $0001) (vSandPotView init:))
		(if (& global104 $0002) (vCrystalPotView init:))
		(if (& global104 $0004) (vMetalPotView init:))
		(if (& global104 $0008) (vTarPotView init:))
		(if (& global104 $0010) (vWoodPotView init:))
		(if (& global104 $0020) (vElectricPotView init:))
		(if (& global104 $0040) (vAshPotView init:))
		(if (& global104 $0080) (vWaterPotView init:))
		(if (& global104 $0100) (vFabricPotView init:))
		(if (& global104 $0200) (vWaxPotView init:))
		(if (!= global105 0) (global108 show:))
		(global102 setSize:)
		(UpdatePlane global102)
	)
	
	(method (dispose)
		(vLeftCorner dispose:)
		(vRightCorner dispose:)
		(vEssenceView cel: 0)
		(vEssenceView dispose:)
		(pEye dispose:)
		(pEyelid cel: 0)
		(pEyelid dispose:)
		(fInventoryWindow dispose:)
		(fUserControls dispose:)
		(if (!= global105 0) (global108 hide:))
		(if (& global104 $0001) (vSandPotView dispose:))
		(if (& global104 $0002) (vCrystalPotView dispose:))
		(if (& global104 $0004) (vMetalPotView dispose:))
		(if (& global104 $0008) (vTarPotView dispose:))
		(if (& global104 $0010) (vWoodPotView dispose:))
		(if (& global104 $0020) (vElectricPotView dispose:))
		(if (& global104 $0040) (vAshPotView dispose:))
		(if (& global104 $0080) (vWaterPotView dispose:))
		(if (& global104 $0100) (vFabricPotView dispose:))
		(if (& global104 $0200) (vWaxPotView dispose:))
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance vLeftCorner of View
	(properties
		view 904
		loop 1
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance vRightCorner of View
	(properties
		view 904
		loop 2
		cel 1
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance fUserControls of Feature
	(properties)
	
	(method (init)
		(self
			createPoly:
				258
				178
				259
				180
				261
				181
				265
				182
				268
				182
				272
				181
				273
				178
				271
				175
				267
				174
				263
				174
				259
				176
				258
				177
		)
		(super init: gNewCast)
		(features delete: self)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(event localize: global102)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(== global180 0)
				(> curRoomNum 999)
				(!= curRoomNum 992)
				(user canControl:)
			)
			(event claimed: 1)
			(curRoom setScript: sUser)
		)
		(return 0)
	)
)

(instance vEssenceView of View
	(properties
		priority 5
		fixPriority 1
		view 904
		loop 4
	)
	
	(method (init)
		(if (== global109 0)
			(self cel: 10)
		else
			(self cel: (- (mod global109 11) 1))
		)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance pEye of Prop
	(properties
		priority 1
		fixPriority 1
		view 904
		loop 5
	)
	
	(method (init)
		(super init: gNewCast)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(event localize: global102)
		(if
			(and
				(self onMe: event)
				(& (event type?) evMOUSEBUTTON)
				(!= global105 0)
				(user canControl:)
				(!= curRoomNum 990)
			)
			(event claimed: 1)
			(if (== curRoomNum 992)
				(curRoom newRoom: prevRoomNum)
			else
				(curRoom newRoom: 992)
			)
		)
	)
)

(instance pEyelid of Prop
	(properties
		priority 2
		fixPriority 1
		view 904
		loop 9
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(theDoits delete: self)
		(super dispose: &rest)
	)
)

(instance fInventoryWindow of Feature
	(properties
		nsLeft 40
		nsTop 146
		nsRight 75
		nsBottom 190
	)
	
	(method (init)
		(super init: gNewCast)
		(features delete: self)
		(mouseDownHandler add: self)
		(self plane: global102)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(event localize: global102)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(> curRoomNum 999)
				(!= curRoomNum 992)
				(user canControl:)
			)
			(event claimed: 1)
			(cond 
				((== global180 1)
					(= global180 0)
					(normalCursor show:)
					(global108 x: 57 y: 185 view: global105 loop: 0)
					(UpdateScreenItem global108)
					(theDoits delete: global108)
				)
				((!= global105 0)
					(= global180 1)
					(normalCursor hide:)
					(UpdateScreenItem global108)
					(theDoits add: global108)
				)
			)
		)
		(return 0)
	)
)

(instance vSandPotView of View
	(properties
		view 904
		loop 3
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance vCrystalPotView of View
	(properties
		view 904
		loop 3
		cel 1
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance vMetalPotView of View
	(properties
		view 904
		loop 3
		cel 2
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance vTarPotView of View
	(properties
		view 904
		loop 3
		cel 3
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance vWoodPotView of View
	(properties
		view 904
		loop 3
		cel 4
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance vElectricPotView of View
	(properties
		view 904
		loop 3
		cel 5
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance vAshPotView of View
	(properties
		view 904
		loop 3
		cel 6
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance vWaterPotView of View
	(properties
		view 904
		loop 3
		cel 7
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance vFabricPotView of View
	(properties
		view 904
		loop 3
		cel 8
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance vWaxPotView of View
	(properties
		view 904
		loop 3
		cel 9
	)
	
	(method (init)
		(super init: gNewCast)
	)
	
	(method (dispose)
		(gNewCast delete: self)
		(super dispose: &rest)
	)
)

(instance sUser of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(sounds play: 15008 0 90 self)
			)
			(1
				(curRoom newRoom: 990)
				(self dispose:)
			)
		)
	)
)

(instance sEyeOpen of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theDoits add: pEyelid)
				(pEyelid setCycle: End self)
			)
			(1
				(theDoits delete: pEyelid)
				(self dispose:)
			)
		)
	)
)

(instance sEyeClose of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theDoits add: pEyelid)
				(pEyelid setCycle: Beg self)
			)
			(1
				(theDoits delete: pEyelid)
				(self dispose:)
			)
		)
	)
)
